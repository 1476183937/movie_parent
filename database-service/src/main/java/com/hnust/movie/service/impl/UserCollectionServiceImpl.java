package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.UserCollectionMapper;
import com.hnust.movie.mapper.UserInfoMapper;
import com.hnust.movie.service.UserCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:53
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Resource
    private UserCollectionMapper userCollectionMapper;

}
