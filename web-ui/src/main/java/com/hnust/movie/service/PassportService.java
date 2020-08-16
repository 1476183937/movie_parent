package com.hnust.movie.service;

import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/26 18:11
 */
@Component
@FeignClient(value = "passport-server")
public interface PassportService {

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
    public String verify(@RequestParam(value = "token") String token,@RequestParam(value = "currentIp") String currentIp);

    /**
     *@title:
     *@description: 登录验证
     *@param: userInfo
     *@author:ggh
     *@updateTime: 2020/5/26 13:18
     **/
    @RequestMapping("/login")
    @ResponseBody
//    public ResultEntity<String> login(UserInfo userInfo);
    public ResultEntity<String> login(@RequestBody UserInfo userInfo, @RequestParam("ip") String ip);

    }
