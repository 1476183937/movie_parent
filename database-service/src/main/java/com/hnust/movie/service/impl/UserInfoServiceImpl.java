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

    /**
    *@title:
    *@description: 注册用户
    *@param: userInfo
    *@author:ggh
    *@updateTime: 2020/8/19 9:40
    **/
    @Override
    public int registyUser(UserInfo userInfo) {

        //result为成功数
        int result = userInfoMapper.registyUser(userInfo);

        return result;
    }

    /**
    *@title:
    *@description: 检查指定用户名是否存在
    *@param: userName
    *@author:ggh
    *@updateTime: 2020/8/19 9:41
    **/
    @Override
    public UserInfo existUserName(String userName) {

        //返回指定用户名的用户id
        UserInfo UserInfo = userInfoMapper.existUserName(userName);

        return UserInfo;
    }

}
