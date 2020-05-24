package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Replay;
import com.hnust.movie.entity.po.ReplayExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplayMapper {
    int countByExample(ReplayExample example);

    int deleteByExample(ReplayExample example);

    int deleteByPrimaryKey(String rid);

    int insert(Replay record);

    int insertSelective(Replay record);

    List<Replay> selectByExample(ReplayExample example);

    Replay selectByPrimaryKey(String rid);

    int updateByExampleSelective(@Param("record") Replay record, @Param("example") ReplayExample example);

    int updateByExample(@Param("record") Replay record, @Param("example") ReplayExample example);

    int updateByPrimaryKeySelective(Replay record);

    int updateByPrimaryKey(Replay record);
}