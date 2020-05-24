package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.ScanHistory;
import com.hnust.movie.entity.po.ScanHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScanHistoryMapper {
    int countByExample(ScanHistoryExample example);

    int deleteByExample(ScanHistoryExample example);

    int deleteByPrimaryKey(String scanId);

    int insert(ScanHistory record);

    int insertSelective(ScanHistory record);

    List<ScanHistory> selectByExample(ScanHistoryExample example);

    ScanHistory selectByPrimaryKey(String scanId);

    int updateByExampleSelective(@Param("record") ScanHistory record, @Param("example") ScanHistoryExample example);

    int updateByExample(@Param("record") ScanHistory record, @Param("example") ScanHistoryExample example);

    int updateByPrimaryKeySelective(ScanHistory record);

    int updateByPrimaryKey(ScanHistory record);
}