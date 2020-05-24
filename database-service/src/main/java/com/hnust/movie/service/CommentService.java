package com.hnust.movie.service;

import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.vo.CommentVO;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:49
 */

public interface CommentService {

    List<CommentVO> selectCommentByMid(Long mid,int startPage);

    //添加评论
    int insertComment(Comment comment);

}
