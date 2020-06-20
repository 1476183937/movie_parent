package com.hnust.movie.service;

import com.hnust.movie.entity.po.UserInfo;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:53
 */
public interface UserInfoService {

    UserInfo login(UserInfo userInfo);

    //添加token到缓存
//    void addTokenToCache(String token,String userId);

}
