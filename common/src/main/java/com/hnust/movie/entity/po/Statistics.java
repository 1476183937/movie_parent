package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 电影个指标的统计表
*@author:ggh
*@updateTime: 2020/5/13 11:18
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics implements Serializable {

    private Long sid;       //统计id
    private Long mid;       //电影id
    private Long ratingNum; //评分数
    private Long collectNum;//收藏数
    private Long playNum;   //播放量
    private String time;    //统计时间


}