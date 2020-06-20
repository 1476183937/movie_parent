package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.mapper.UserInfoMapper;
import com.hnust.movie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

//    @Autowired



    @Override
    public UserInfo login(UserInfo userInfo) {

        List<UserInfo> userInfo1 = null;

        userInfo1 = userInfoMapper.login(userInfo);

        if (userInfo1.size() > 0){

            return userInfo1.get(0);
        }

        return null;

    }

}
