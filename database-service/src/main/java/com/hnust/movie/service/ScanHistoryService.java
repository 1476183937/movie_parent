package com.hnust.movie.service;

import com.hnust.movie.entity.po.ScanHistory;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:52
 */
public interface ScanHistoryService {

    //查
    List<ScanHistory> getHistory(Long uid);

    //增
    int addHistory(ScanHistory scanHistory);

    //删
    int deleteHistory(String scanId);

}
