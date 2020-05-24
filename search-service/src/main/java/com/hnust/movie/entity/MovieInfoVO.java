package com.hnust.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/18 21:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoVO {


    private Long mid;               //id
    private String movieName;       //名称
    private String imgUrls;         //图片
    private String directors;       //导演
    private String screenWriter;    //编剧
    private String mainActors;      //主演
    private String categories;      //类别
    private String location;        //发行的位置
    private String language;        //语言
    private int releaseDate;     //发布时间
//    private int time;     //发布时间
    private String runTime;         //时长
    private String alias;           //别名
    private String summery;         //简介
    private Float ratingNum;        //评分值
    private Integer offShelf;       //是否下架了->0：未下架 1：已被下架
    private Integer hotDegree;      //热度
    private Long viewNum;           //观看人数
    private String playUrl;         //视频播放地址

}
