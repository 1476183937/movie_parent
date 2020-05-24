package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:用于接收从数据库查询到的评论数据
 * @Author: ggh
 * @Date: 2020/5/14 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO implements Serializable {

    /*private int count;                  //总评论数
    private int totalPage;              //总页数
    private int currentPage;            //当前页
    private int nextPage;               //下一页
    private int prePage;                //前一页
    private List<Comment> commentList;  //评论列表*/

    private String commentId; //评论id
    private Long uid;         //用户id
    private Long mid;         //电影id
    private String content;   //评论内容
    private String date;      //评论日期
    private int likeNum;      //点赞人数
    private int dislikeNum;   //踩的人数
    private String username;  //用户名称
    private String face;      //头像url
    private int count;        //评论的数量


}
