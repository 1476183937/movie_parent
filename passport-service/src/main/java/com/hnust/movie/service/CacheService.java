package com.hnust.movie.service;

import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/26 13:40
 */
@Component
@FeignClient(value = "cache-service")
public interface CacheService {

    /**
     *@title:
     *@description: 添加token到缓存
     *@param: token
     *@param: userId
     *@author:ggh
     *@updateTime: 2020/5/26 13:33
     **/
    @PostMapping("/cache/add/token")
    @ResponseBody
    public ResultEntity addTokenToCache(@RequestParam(value = "token",required = true)String token,@RequestParam(value = "userId",required = true) String userId);


    /**
     *@title:
     *@description: 根据key获取相应的值
     *@param: key
     *@author:ggh
     *@updateTime: 2020/5/26 13:58
     **/
    @RequestMapping("/cache/get")
    @ResponseBody
    public ResultEntity<String> get(@RequestParam(value = "key",required = true)String key);

    /**
     *@title:
     *@description: 设置kv对，同时设置过期时间
     *@param: key
     *@param: time：过期时间
     *@param: value
     *@author:ggh
     *@updateTime: 2020/5/26 14:19
     **/
    @PostMapping("/cache/setex")
    @ResponseBody
    public ResultEntity<String> setex(@RequestParam(value = "key",required = true)String key,@RequestParam(value = "time",required = true)int time,@RequestParam(value = "value",required = true)String value);


}
