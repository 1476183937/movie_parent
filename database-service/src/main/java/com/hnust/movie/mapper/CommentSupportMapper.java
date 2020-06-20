package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.CommentSupport;
import org.apache.ibatis.annotations.Insert;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/31 11:43
 */
public interface CommentSupportMapper {

    //添加点赞记录
    @Insert("INSERT INTO `m_comment_support`(`support_id`, `comment_id`, `uid`, `date`," +
            " `cancel`) VALUES (#{supportId}, #{commentId}, #{uid}, #{date}, 0);")
    int insertSupport(CommentSupport commentSupport);



}
