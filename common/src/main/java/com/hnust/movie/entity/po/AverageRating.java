package com.hnust.movie.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 电影的平均评分表
*@author:ggh
*@updateTime: 2020/5/13 11:29
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageRating implements Serializable {

    private Long avgId;             //计算id
    private Long mid;               //电影id
    private String averageRating;   //平均评分
    private String date;            //计算日期

}