package com.hnust.movie.controller;

import com.hnust.movie.common.CommonUtil;
import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.Rating;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.CommentVO2;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title:电影详情页的controller
 * @Author: ggh
 * @Date: 2020/5/15 17:49
 */
@Controller
public class detailController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CacheService cacheService;

    @PostMapping("/comment/publishComment")
    @ResponseBody
    public ResultEntity<CommentVO2> publishComment(@RequestBody Comment comment, ModelMap modelMap){

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

    /**
    *@title:
    *@description: 播放指定电影id的视频
    *@author:ggh
    *@updateTime: 2020/5/16 10:39
    **/
    @RequestMapping("/play/{movieId}.html")
    public String play(@PathVariable("movieId") Long movieId, ModelMap modelMap){

        /*//获取相关的详情数据和评论数据
        boolean result = CommonUtil.getMovieInfoAndComments(databaseService,cacheService, movieId, 1, modelMap);

        if (false){
            //TODO:获取 “喜欢看【xxx】的人也喜欢” 的数据

            //TODO:获取本周本月热播榜的数据

            //
        }*/

        int totalPage = 0, count = 0;
        ResultEntity<List<CommentVO>> commentByMid = null;
        ResultEntity<CommentVO> commentVOResultEntity = null;

        ResultEntity<MovieInfo> movieInfoFromCache = cacheService.getMovieInfoFromCache(movieId);
        //如果没有得到数据或数据为空
        if (movieInfoFromCache.getData() == null || "".equals(movieInfoFromCache.getData()) ||
                "NO_DATA".equals(movieInfoFromCache.getData())){
//            modelMap.addAttribute("movieInfo",ResultEntity.failed("NO DATA!!!"));

            //TODO:跳到错误页面（待实现）
            return "error";
        }else {
            //如果得到了数据
            modelMap.addAttribute("movieInfo",movieInfoFromCache);

            //获取评论数据

            commentByMid = databaseService.getCommentByMid(movieId, 1);

            //计算总记录数
            if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 != 0) {
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                totalPage++; //评论总页数
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }else if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 == 0){
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }

            //封装评论返回结果
            CommentVO2 commentVO2 = new CommentVO2();
            commentVO2.setCount(count);
            commentVO2.setCurrentPage(1);
            commentVO2.setNextPage(2);
            commentVO2.setPrePage(1);
            commentVO2.setTotalPage(totalPage);
            commentVO2.setCommentVOList(commentByMid.getData());


            modelMap.addAttribute("commentResult", ResultEntity.successWithData(commentVO2));


        }


        return "moviePlay";
    }

    /**
    *@title:
    *@description: 用户给电影评分
    *@param: mid：电影id
    *@param: ratingNum：评分值
    *@author:ggh
    *@updateTime: 2020/5/24 11:24
    **/
    @RequestMapping("/detail/add/rating")
    @ResponseBody
    public ResultEntity rating(@RequestBody Rating rating){

        //TODO:获取用户id
        Long uid = 22960758l;

        //判断该用户是否对该电影评分过
        ResultEntity resultEntity = databaseService.isScored(uid, rating.getMid());
        if ("SUCCESS".equals(resultEntity.getResult())){
            return ResultEntity.failed("您已对该电影评分过");
        }else {
            //如果没有评分过写入数据库
            Rating rating1 = new Rating();
            rating1.setUid(uid);
            rating1.setMid(rating.getMid());
            rating1.setRating(rating.getRating());
            ResultEntity result = databaseService.addRating(rating1);

            if ("SUCCESS".equals(result.getResult())){
                return ResultEntity.successNoData();
            }else {
                return ResultEntity.failed("评分失败");
            }

        }


    }


}
