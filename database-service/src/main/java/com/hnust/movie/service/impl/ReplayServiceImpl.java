package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.ReplayMapper;
import com.hnust.movie.service.ReplayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:51
 */
@Service
public class ReplayServiceImpl implements ReplayService {

    @Resource
    private ReplayMapper replayMapper;

}
