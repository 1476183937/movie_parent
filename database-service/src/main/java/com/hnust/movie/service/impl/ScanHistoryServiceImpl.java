package com.hnust.movie.service.impl;

import com.hnust.movie.mapper.ScanHistoryMapper;
import com.hnust.movie.service.ScanHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:52
 */
@Service
public class ScanHistoryServiceImpl implements ScanHistoryService {

    @Resource
    private ScanHistoryMapper scanHistoryMapper;

}
