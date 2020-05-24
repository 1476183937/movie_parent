package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfo implements Serializable {

    private Long mid;               //id
    private String movieName;       //名称
    private String imgUrls;         //图片
    private String directors;       //导演
    private String screenWriter;    //编剧
    private String mainActors;      //主演
    private String categories;      //类别
    private String location;        //发行的位置
    private String language;        //语言
    private String releaseDate;     //发布时间
    private String runTime;         //时长
    private String alias;           //别名
    private String summery;         //简介
    private Float ratingNum;        //评分值
    private Integer offShelf;       //是否下架了->0：未下架 1：已被下架
    private Integer hotDegree;      //热度
    private Long viewNum;           //观看人数
    private String playUrl;         //视频播放地址
    private String extend2;         //扩展字段

}