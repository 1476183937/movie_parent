package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.StatisticsMapper;
import com.hnust.movie.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:52
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

}
