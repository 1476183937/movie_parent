package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 用户观看电影的历史记录表
*@author:ggh
*@updateTime: 2020/5/13 11:17
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanHistory implements Serializable {

    private String scanId;    //观看id
    private Long uid;         //用户id
    private Long mid;         //电影id
    private String movieName; //电影名称
    private String image;     //电影的图片url
    private String date;      //观看时间
    private Integer deleted;  //是否被删除了->0:未被删除 1：已被删除


}