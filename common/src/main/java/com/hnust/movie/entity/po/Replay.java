package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Replay implements Serializable {

    private String rid;         //回复id
    private Long uid;           //发表回复的用户d
    private String commentId;   //回复的评论id
    private String content;     //回复的内容
    private String date;        //发表回复的日期
    private Integer like;       //回复的点赞数
    private Integer dislike;    //踩的数量
    private Integer deleted;    //是否被删除了->0:未被删除 1：被删除

}