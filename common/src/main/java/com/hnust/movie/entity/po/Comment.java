package com.hnust.movie.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 电影的评论表
*@author:ggh
*@updateTime: 2020/5/13 11:28
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    private String commentId; //评论id
    private Long uid;         //用户id
    private Long mid;         //导演id
    private String content;   //评论内容
    private String date;      //评论日期
    private Integer deleted;  //是否被删除了->0:未被删除 1：已被删除
    private Integer likeNum;     //点赞的人数
    private Integer dislikeNum;  //踩的人数
    private String extend1;   //扩展字段


}