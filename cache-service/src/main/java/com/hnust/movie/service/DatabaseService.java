package com.hnust.movie.service;

import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.*;
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

    /**
    *@title:
    *@description: 根据电影id获取电影的详情数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/db/detailInfo/{movieId}")
    @ResponseBody
//    public ResultEntity<MovieInfo> getDetailInfo(@PathVariable("movieId") Long movieId);
    public ResultEntity<MovieInfoInCache> getDetailInfo(@PathVariable("movieId") Long movieId);

    /**
    *@title:
    *@description: 根据电影id获取相关的评论数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/db/detailInfo/comment/{movieId}")
    @ResponseBody
    public ResultEntity<List<CommentVO>> getCommentByMid(@PathVariable("movieId") Long movieId,
                                                         @RequestParam(value = "startPage", required = false, defaultValue = "1") int startPage);


    @RequestMapping("/db/movies/list")
    @ResponseBody
    public ResultEntity<MovieListVO> getMovieListByMultipleCategory();

    @RequestMapping("/db/comics/list")
    @ResponseBody
    public ResultEntity<ComicListVO> getComicListByMultipleCategory();

}
