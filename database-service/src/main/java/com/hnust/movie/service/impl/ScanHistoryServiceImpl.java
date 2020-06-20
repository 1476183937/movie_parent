package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.ScanHistory;
import com.hnust.movie.mapper.ScanHistoryMapper;
import com.hnust.movie.service.ScanHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:52
 */
@Service
public class ScanHistoryServiceImpl implements ScanHistoryService {

    @Resource
    private ScanHistoryMapper scanHistoryMapper;

    @Override
    public List<ScanHistory> getHistory(Long uid) {

        List<ScanHistory> scanHistories = null;

        scanHistories = scanHistoryMapper.queryByUid(uid);

        return scanHistories;
    }

    @Override
    public int addHistory(ScanHistory scanHistory) {
        int result = 0;
        result = scanHistoryMapper.insertHistory(scanHistory);

        return result;
    }

    //删除记录
    @Override
    public int deleteHistory(String scanId) {

        int result = 0;
        scanHistoryMapper.deleteHistoty(scanId);

        return result;
    }
}
