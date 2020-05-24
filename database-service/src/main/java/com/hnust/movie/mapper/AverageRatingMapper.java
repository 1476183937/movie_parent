package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.AverageRating;
import com.hnust.movie.entity.po.AverageRatingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AverageRatingMapper {
    int countByExample(AverageRatingExample example);

    int deleteByExample(AverageRatingExample example);

    int deleteByPrimaryKey(Long avgId);

    int insert(AverageRating record);

    int insertSelective(AverageRating record);

    List<AverageRating> selectByExample(AverageRatingExample example);

    AverageRating selectByPrimaryKey(Long avgId);

    int updateByExampleSelective(@Param("record") AverageRating record, @Param("example") AverageRatingExample example);

    int updateByExample(@Param("record") AverageRating record, @Param("example") AverageRatingExample example);

    int updateByPrimaryKeySelective(AverageRating record);

    int updateByPrimaryKey(AverageRating record);
}