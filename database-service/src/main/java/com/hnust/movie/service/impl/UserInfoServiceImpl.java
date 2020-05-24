package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.UserInfoMapper;
import com.hnust.movie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:53
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

//    @Autowired
    @Resource
    private UserInfoMapper userInfoMapper;

}
