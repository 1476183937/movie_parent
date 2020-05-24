package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Rating;
import com.hnust.movie.entity.po.RatingExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RatingMapper {


    //判断用户对某个电影是否评分过
    @Select("SELECT * FROM m_rating WHERE uid=#{uid} AND mid=#{mid}")
    Rating queryIsScored(@Param("uid") Long uid, @Param("mid") Long mid);

    //添加评分
    @Insert("INSERT INTO `m_rating` VALUES (#{ratingId}, #{uid}, ${mid}, #{rating}, #{date});")
    int insertRating(Rating rating);

    int countByExample(RatingExample example);

    int deleteByExample(RatingExample example);

    int deleteByPrimaryKey(Long ratingId);

    int insert(Rating record);

    int insertSelective(Rating record);

    List<Rating> selectByExample(RatingExample example);

    Rating selectByPrimaryKey(Long ratingId);

    int updateByExampleSelective(@Param("record") Rating record, @Param("example") RatingExample example);

    int updateByExample(@Param("record") Rating record, @Param("example") RatingExample example);

    int updateByPrimaryKeySelective(Rating record);

    int updateByPrimaryKey(Rating record);
}