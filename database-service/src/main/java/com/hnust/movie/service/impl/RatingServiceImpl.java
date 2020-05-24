package com.hnust.movie.service.impl;

import com.hnust.movie.config.IdGeneratorSnowflake;
import com.hnust.movie.entity.po.Rating;
import com.hnust.movie.mapper.RatingMapper;
import com.hnust.movie.service.RatingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:51
 */

@Service
public class RatingServiceImpl implements RatingService {

    @Resource
    private RatingMapper ratingMapper;

    //引入雪花算法，生成唯一id
    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;


    /**
    *@title:
    *@description: 判断用户对某个电影是否评分过
    *@param: uid：用户id
    *@param: mid：电影id
    *@author:ggh
    *@updateTime: 2020/5/24 12:15
    **/
    @Override
    public Rating isScored(Long uid, Long mid) {
        Rating rating = ratingMapper.queryIsScored(uid, mid);
        return rating;
    }

    /**
    *@title:
    *@description: 添加评论
    *@param: rating
    *@author:ggh
    *@updateTime: 2020/5/24 12:15
    **/
    @Override
    public int addRating(Rating rating) {

        //生成唯一id
        long id = idGeneratorSnowflake.snowflakeId(1, 1);

        if (StringUtils.isNotBlank(id+"")){
            rating.setRatingId(id);
        }

        //补全日期
        if (StringUtils.isBlank(rating.getDate())) {
            long timeMillis = System.currentTimeMillis();
            Date time = new Date(timeMillis);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
            rating.setDate(date);
        }

        int result = ratingMapper.insertRating(rating);

        return result;
    }
}
