package com.hnust.movie.controller;

import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.CommentVO2;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Title:用于评论操作的controller
 * @Author: ggh
 * @Date: 2020/5/16 20:42
 */
@Controller
public class CommentController {

    @Autowired
    private DatabaseService databaseService;

    /**
    *@title:
    *@description: 获取评论
    *@author:ggh
    *@updateTime: 2020/5/16 21:35
    **/
    @RequestMapping("/comment/get")
    @ResponseBody
    public ResultEntity getComments(
            @RequestParam("mid") Long movieId,
            @RequestParam(value = "startPage",defaultValue = "1") int startPage,
            ModelMap modelMap
    ){

        ResultEntity<List<CommentVO>> commentByMid = databaseService.getCommentByMid(movieId, startPage);

        int totalPage = 0, count = 0;
        if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 != 0) {
            totalPage = commentByMid.getData().get(0).getCount() / 10;
            totalPage++; //评论总页数
            count = commentByMid.getData().get(0).getCount(); //评论总条数
        }else if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 == 0){
            totalPage = commentByMid.getData().get(0).getCount() / 10;
            count = commentByMid.getData().get(0).getCount(); //评论总条数
        }

        CommentVO2 commentVO2 = new CommentVO2();
        commentVO2.setCount(count);
        commentVO2.setCurrentPage(startPage);
        commentVO2.setTotalPage(totalPage);
        commentVO2.setNextPage((startPage+1)>=totalPage?totalPage:(startPage+1));
        commentVO2.setPrePage((startPage-1)<=1?1:(startPage-1));
        commentVO2.setCommentVOList(commentByMid.getData());

        return ResultEntity.successWithData(commentVO2);
    }


}
