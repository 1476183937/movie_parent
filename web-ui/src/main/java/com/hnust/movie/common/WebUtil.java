package com.hnust.movie.common;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.service.PassportService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/31 11:31
 */
public class WebUtil {

    //获取用户id
    public static String getUserId(HttpServletRequest request, PassportService passportService){

        //获取token
        Cookie[] cookies = request.getCookies();
        String userToken = "";
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("userToken".equals(cookie.getName())){
                    userToken = cookie.getValue();
                    break;
                }
            }

            if (StringUtils.isNotBlank(userToken)){
                String ip = getIpByRequest(request);

                //验证token
                String verify = passportService.verify(userToken, ip);
                Map userMap = JSONObject.parseObject(verify, Map.class);

                //获取用户id
                String userId = "";
                if ("success".equals(userMap.get("status"))){
                    if (userMap != null){
                        userId = (String) userMap.get("userId");
                        return userId;
                    }
                }

            }

        }

        return null;
    }

    /**
     *@title:
     *@description: 根据request获取ip
     *@param: request
     *@author:ggh
     *@updateTime: 2020/5/27 20:16
     **/
    public static String getIpByRequest(HttpServletRequest request){

        //获取ip
        String ip = request.getHeader("x-forwarded-for");//获取通过ngnix转发的客户端ip
        if (StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr(); //获取request里的ip

            //如果request里也没有ip，就直接指定
            if (StringUtils.isBlank(ip)){
                ip = "127.0.0.1";
            }
        }

        return ip;
    }


}
