package com.hnust.movie.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/31 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSupport implements Serializable {

    private String supportId;   //点赞id
    private String commentId;   //评论id
    private Long uid;           //用户id
    private String date;        //点赞日期
    private int cancel;         //是否取消了点赞->0:未取消 1:取消了

}
