package com.hnust.movie.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 返回给前端的评论包装类
 * @Author: ggh
 * @Date: 2020/5/17 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO2 implements Serializable {

    private int currentPage; //当前页
    private int totalPage;   //总页数
    private int count;       //评论总数量
    private int nextPage;    //下一页
    private int prePage;     //前一页

    private List<CommentVO> commentVOList; //评论列表

}
