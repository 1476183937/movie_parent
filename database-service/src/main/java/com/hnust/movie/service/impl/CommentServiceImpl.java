package com.hnust.movie.service.impl;

import com.hnust.movie.config.IdGeneratorSnowflake;
import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.mapper.CommentMapper;
import com.hnust.movie.service.CommentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:50
 */
@Service
public class CommentServiceImpl implements CommentService {

    //    @Autowired
    @Resource
    private CommentMapper commentMapper;

    //引入雪花算法，生成唯一id
    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * @title:
     * @description: 根据电影id获取相关的评论信息
     * @author:ggh
     * @updateTime: 2020/5/14 17:06
     **/
    @Override
    public List<CommentVO> selectCommentByMid(Long mid, int startPage) {

        startPage = (startPage - 1) * 10;
        List<CommentVO> commentVOS = commentMapper.selectCommentByMid(mid, startPage);

        return commentVOS;
    }

    /**
     * @title:
     * @description: 添加评论
     * @author:ggh
     * @updateTime: 2020/5/15 18:01
     **/
    @Override
    public int insertComment(Comment comment) {

        //补全日期
        if (StringUtils.isBlank(comment.getDate())) {
            long timeMillis = System.currentTimeMillis();
            Date time = new Date(timeMillis);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
            comment.setDate(date);
        }

        //设置commentId,用雪花算法
        long id = idGeneratorSnowflake.snowflakeId(1, 1);
        String commentId = null;
        if (StringUtils.isNotBlank(id+"")){
            commentId = String.valueOf(id);
        }
        comment.setCommentId(commentId);

        int result = commentMapper.insertComment(comment);

        return result;
    }
}
