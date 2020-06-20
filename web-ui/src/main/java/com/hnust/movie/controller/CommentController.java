package com.hnust.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.annotation.LoginRequired;
import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.CommentVO2;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.PassportService;
import com.hnust.movie.util.CommonUtil;
import com.hnust.movie.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Title:用于评论操作的controller
 * @Author: ggh
 * @Date: 2020/5/16 20:42
 */
@Controller
public class CommentController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private PassportService passportService;

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


    /**
    *@title:
    *@description: 发表评论
    *@param: comment
    *@param: modelMap
    *@author:ggh
    *@updateTime: 2020/5/26 18:44
    **/
    @PostMapping("/comment/publishComment")
    @ResponseBody
    @LoginRequired(mustLogin = true)
    public ResultEntity<CommentVO2> publishComment(@RequestBody Comment comment, HttpServletRequest request, ModelMap modelMap){

        //获取uid
        String oldToken = CookieUtil.getCookieValue(request, "userToken", true);

        //获取ip
        String ip = CommonUtil.getIpByRequest(request);

        //验证token
        String verify = passportService.verify(oldToken, ip);
        Map userMap = JSONObject.parseObject(verify, Map.class);
        String userId = "";
        if(userMap != null){
            userId = (String) userMap.get("userId");

        }else {
            //解析失败了，就要用户重新登录
            return ResultEntity.failed("REDIRECT_LOGIN");
        }

        comment.setUid(Long.parseLong(userId));

        ResultEntity resultEntity = databaseService.addComment(comment);

        ResultEntity<List<CommentVO>> commentByMid = null;
        if ("SUCCESS".equals(resultEntity.getResult())){

            //添加成功后获取最新评论数据
            commentByMid = databaseService.getCommentByMid(comment.getMid(), 1);

            modelMap.addAttribute("commentList",commentByMid);
        }
        //计算总记录数
        int totalPage = 0, count = 0;
        if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 != 0) {
            totalPage = commentByMid.getData().get(0).getCount() / 10;
            totalPage++; //评论总页数
            count = commentByMid.getData().get(0).getCount(); //评论总条数
        }

        CommentVO2 commentVO2 = new CommentVO2();
        commentVO2.setCurrentPage(1);
        commentVO2.setPrePage(1);
        commentVO2.setTotalPage(totalPage);
        commentVO2.setNextPage(2>=totalPage?totalPage:2);
        commentVO2.setCount(count);
        commentVO2.setCommentVOList(commentByMid.getData());


//        return commentByMid;
        return ResultEntity.successWithData(commentVO2);
    }


}
