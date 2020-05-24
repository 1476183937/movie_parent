package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.AverageRatingMapper;
import com.hnust.movie.service.AverageRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:46
 */

@Service
public class AverageRatingServiceImpl implements AverageRatingService {


//    @Autowired
    @Resource
    private AverageRatingMapper averageRatingMapper;


}
