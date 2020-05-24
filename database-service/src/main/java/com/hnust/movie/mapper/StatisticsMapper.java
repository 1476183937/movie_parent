package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Statistics;
import com.hnust.movie.entity.po.StatisticsExample;
import com.hnust.movie.entity.po.StatisticsKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper {
    int countByExample(StatisticsExample example);

    int deleteByExample(StatisticsExample example);

    int deleteByPrimaryKey(StatisticsKey key);

    int insert(Statistics record);

    int insertSelective(Statistics record);

    List<Statistics> selectByExample(StatisticsExample example);

    Statistics selectByPrimaryKey(StatisticsKey key);

    int updateByExampleSelective(@Param("record") Statistics record, @Param("example") StatisticsExample example);

    int updateByExample(@Param("record") Statistics record, @Param("example") StatisticsExample example);

    int updateByPrimaryKeySelective(Statistics record);

    int updateByPrimaryKey(Statistics record);
}