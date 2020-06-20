package com.hnust.movie.service;

import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/26 12:26
 */
@Component
@FeignClient(value = "database-server")
public interface DatabaseService {

    @RequestMapping("/login")
    @ResponseBody
    public ResultEntity<UserInfo> login(UserInfo userInfo);

}
