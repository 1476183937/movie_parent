package com.hnust.movie.service;

import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.Rating;
import com.hnust.movie.entity.vo.CategorySearchVO;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.ResultEntity;
import com.sun.org.apache.regexp.internal.RE;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 17:15
 */

@Component
@FeignClient(value = "database-server")
public interface DatabaseService {

    @RequestMapping("/index/{is_comic}")
    @ResponseBody
    public ResultEntity<List<MovieInfo>> getMovieInfoForIndexPage(@PathVariable("is_comic") int is_comic);

    /**
    *@title:
    *@description: 根据电影id获取电影的详情数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/detailInfo/{movieId}")
    @ResponseBody
    public ResultEntity<MovieInfo> getDetailInfo(@PathVariable("movieId") Long movieId);

    /**
    *@title:
    *@description: 根据电影id获取相关的评论数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/detailInfo/comment/{movieId}")
    @ResponseBody
    public ResultEntity<List<CommentVO>> getCommentByMid(@PathVariable("movieId") Long movieId,
                                                         @RequestParam(value = "startPage",required = false,defaultValue = "1") int startPage);


    /**
    *@title:
    *@description: 添加评论
    *@author:ggh
    *@updateTime: 2020/5/15 18:20
    **/
    @PostMapping("/comment/publishComment")
    @ResponseBody
    public ResultEntity addComment(@RequestBody Comment comment);

    /**
     *@title:
     *@description: 获取所有分类信息，包含分类、地区、年份等
     *@param:
     *@author:ggh
     *@updateTime: 2020/5/20 11:05
     **/
    @RequestMapping("/category/all")
    @ResponseBody
    public ResultEntity<CategorySearchVO> getAllCategoriesInfo();

    /**
     *@title:
     *@description: 添加评分
     *@param: rating
     *@author:ggh
     *@updateTime: 2020/5/24 12:23
     **/
    @RequestMapping("/db/add/rating")
    @ResponseBody
    public ResultEntity addRating(@RequestBody Rating rating);

    /**
     *@title:
     *@description:
     *@param: uid
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/24 12:20
     **/
    @RequestMapping("/db/movie/rating")
    @ResponseBody
    public ResultEntity isScored(@RequestParam("uid") Long uid, @RequestParam("mid") Long mid);


}
