package com.hnust.movie.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.annotation.LoginRequired;
import com.hnust.movie.service.PassportService;
import com.hnust.movie.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Title:自定义拦截器：用户登录授权拦截器
 * @Author: ggh
 * @Date: 2020/5/25 20:40
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PassportService passportService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截下来的是一个静态资源，放行
        if (handler instanceof ResourceHttpRequestHandler) {
            System.out.println("preHandle这是一个静态资源方法！");
            return true;
        }
        //获取请求方法上的注解
        LoginRequired methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(LoginRequired.class);

        //如果没有改注解，就不要拦截，放行
        if (methodAnnotation == null){
            return true;
        }

        //是否必须登录的标志:true为必须登录
        boolean mustLogin = methodAnnotation.mustLogin();
        String token = "";

        //oldToken不为空的话说明用户之前登陆过，但过期了
//        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        String oldToken = CookieUtil.getCookieValue(request, "userToken", true);
        if (StringUtils.isNotBlank(oldToken)){
            token = oldToken;
        }

        //获取新的token，该token不为空的话说明用户刚从认证中心回来
        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)){
            token = newToken;
        }

        Map userMap = null;

        //token不为空，就去认证中心验证其真伪
        boolean success = false; //判断验证是否成功
        if (StringUtils.isNotBlank(token)){

            //获取ip
            String ip = "";

            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();
                if (StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }

            //根据token和ip调用验证中心服务进行验证
            String verify = passportService.verify(token, ip);

            userMap = JSONObject.parseObject(verify, Map.class);

            String status = (String)userMap.get("status");

            if ("success".equals(status)){
                //验证通过
                success = true;
            }

        }

        //如果必须登录
        if (mustLogin){

            //token验证不通过
            if (!success){

                StringBuffer url = request.getRequestURL();

                //重定向登录页面,并设置返回url
                redirect(request,response);

                //拦截下来，不放行
                return false;

            }else{ //token验证通过

                //往request域里存放信息
                request.setAttribute("userId",userMap.get("userId"));
                request.setAttribute("nickName",userMap.get("nickName"));
                request.setAttribute("face",userMap.get("face"));

                //将token放入cookie,最大存活时间为一天
                CookieUtil.setCookie(request, response,"userToken",token,60*60*24,true);

            }


        }else{  //不一定要登录也可以的情况

            //token验证通过,不通过就什么也不干，直接放行
            if (success){
                //往request域里存放信息
                request.setAttribute("userId",userMap.get("userId"));
                request.setAttribute("nickName",userMap.get("nickName"));
                request.setAttribute("face",userMap.get("face"));

                //将token放入(覆盖)cookie,最大存活时间为一天
                CookieUtil.setCookie(request, response,"userToken",token,60*60*24,true);

            }

        }


        return true;
    }

    // 对ajax的重定向进行处理
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取返回的url
        String returnUrl = "index";
        if (StringUtils.isNotBlank(request.getParameter("returnUrl"))){
            returnUrl = request.getParameter("returnUrl");
        }

        //获取当前请求的路径：协议://服务器名:端口号/项目名
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();

        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //自定义响应头 告诉ajax我是重定向，用ajax进行判断。
            response.setHeader("REDIRECT_LOGIN", "REDIRECT_LOGIN");
            //自定义响应头 告诉ajax我重定向的路径
            response.setHeader("CONTEXTPATH", basePath+"/login.html?returnUrl="+returnUrl);
            // response.setHeader("Content-Type", "application/json;charset=UTF-8"); 这句没必要
            // 设置这次请求状态码为403 禁止访问
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        }else{
            // 如果不是ajax 就正常重定向
            response.sendRedirect(basePath + request.getRequestURL());
        }
    }

}
