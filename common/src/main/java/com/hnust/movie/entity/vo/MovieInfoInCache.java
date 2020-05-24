package com.hnust.movie.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Title: 在缓存中存储的电影信息,主要存储的是在导航栏的电影/动漫标签页展示的数据，并非详情页的数据
 * @Author: ggh
 * @Date: 2020/5/22 12:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoInCache implements Serializable {

    private Long mid;
    private String movieName;
    private String imgUrls;
    private String categories;
    private String directors;
    private String mainActors;
    private String ratingNum;
    private String summery;

}
