package com.hnust.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/26 12:09
 */
@Controller
public class PassportController {


    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CacheService cacheService;

    /**
    *@title:
    *@description: 根据传过来的token和ip验证其token是否有效
    *@param: token
    *@param: currentIp
    *@author:ggh
    *@updateTime: 2020/5/26 13:13
    **/
    @RequestMapping("/verify")
    @ResponseBody
    public String verify(@RequestParam(value = "token")String token,@RequestParam(value = "currentIp") String currentIp){

        HashMap<String, String> map = new HashMap<>();

        Map<String, Object> decode = JwtUtil.decode(token, "movie", currentIp);

        if (decode != null){
            //解码成功，存入用户id和昵称
            map.put("status","success");
            map.put("userId",decode.get("userId")+"");
            map.put("nickName",(String) decode.get("nickName"));
            map.put("face",(String) decode.get("face"));
        }else{
            //解码失败
            map.put("status","failed");
        }
        return JSONObject.toJSONString(map);

    }

    /**
    *@title:
    *@description: 登录验证
    *@param: userInfo
    *@author:ggh
    *@updateTime: 2020/5/26 13:18
    **/
    @RequestMapping("/login")
    @ResponseBody
    public ResultEntity<String> login(@RequestBody UserInfo userInfo, HttpServletRequest request){

        String token = "";

        if (StringUtils.isNotBlank(userInfo.getUsername()) && StringUtils.isNotBlank(userInfo.getPassword())) {

            UserInfo info = null;

            //先从缓存里获取，判断是否有相关信息
            //缓存中存储的key为->"user:"+username+password+":info",value为userinfo的json字符串
            ResultEntity<String> cacheResult = cacheService.get("user:" + userInfo.getUsername() + userInfo.getPassword() + ":info");

            if ("SUCCESS".equals(cacheResult.getResult())){
                String value = cacheResult.getData();
                if (StringUtils.isNotBlank(value)){

                    info = JSONObject.parseObject(value, UserInfo.class);

                }

            }else { //从缓存里没有获取到就从数据库获取

                //调用数据库的登录功能
                ResultEntity<UserInfo> resultEntity = databaseService.login(userInfo);

                if ("FAILED".equals(resultEntity.getResult())){
                    return ResultEntity.failed("用户名或密码错误!!!");

                }

                if ("SUCCESS".equals(resultEntity.getResult())){
                    info = resultEntity.getData();

                    //将信息存入缓存
                    cacheService.setex("user:"+info.getUsername()+info.getPassword()+":info",60*60*24,JSONObject.toJSONString(info));

                }

            }

            if (info != null){
                //获取到用户信息后，生成token

                //获取ip
                String ip = request.getHeader("x-forwarded-for");//获取通过ngnix转发的客户端ip
                if (StringUtils.isBlank(ip)){
                    ip = request.getRemoteAddr(); //获取request里的ip

                    //如果request里也没有ip，就直接指定
                    if (StringUtils.isBlank(ip)){
                        ip = "127.0.0.1";
                    }
                }

                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("userId",info.getUid());
                userMap.put("nickName",info.getNickName());
                userMap.put("face",info.getFace());

                //使用jwt，生成token
                token = JwtUtil.encode("movie", userMap, ip);

                //调用服务，存入redis,由缓存服务来存，而不是认证中心自己来向缓存里存数据
                cacheService.addTokenToCache(token, info.getUid()+"");

                //返回结果
                return ResultEntity.successWithData(token);

            }

        }

        return ResultEntity.failed("login fail");

    }

}
