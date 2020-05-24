package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 用户的电影收藏表
*@author:ggh
*@updateTime: 2020/5/13 11:20
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollection implements Serializable {

    private String collectionId; //收藏id
    private Long mid;            //电影id
    private Long uid;            //用户id
    private String image;        //电影图片url
    private String movieName;    //电影名称
    private String date;         //收藏日期
    private String deleted;      //是否被删除了->0:未被删除 1：已被删除


}