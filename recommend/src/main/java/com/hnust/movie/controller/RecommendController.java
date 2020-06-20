package com.hnust.movie.controller;

import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 17:54
 */
@Controller
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
    *@title:
    *@description: 获取电影排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/6/18 17:59
    **/
    @RequestMapping("/recommend/top/movies")
    @ResponseBody
    public ResultEntity getTopMovies(){

        ResultEntity resultEntity = recommendService.getTopMovies();

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取动漫排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/6/18 17:59
    **/
    @RequestMapping("/recommend/top/comics")
    @ResponseBody
    public ResultEntity getTopComics(){

        ResultEntity resultEntity = recommendService.getTopComics();

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取与某个电影相似的电影列表
    *@param: mid：电影id
    *@author:ggh
    *@updateTime: 2020/6/18 18:00
    **/
    @RequestMapping("/recommend/similar/movies/{mid}")
    @ResponseBody
    public ResultEntity<SimilarMovieRecommendation> getSimilarMovieRecommendation(@PathVariable("mid") Long mid){

        ResultEntity<SimilarMovieRecommendation> resultEntity = recommendService.getSimilarMovieRecommendation(mid);

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取每月的电影排行榜(包含动漫)
    *@param:
    *@author:ggh
    *@updateTime: 2020/6/18 18:00
    **/
    @RequestMapping("/recommend/month/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfMonth(){

        ResultEntity resultEntity = recommendService.getTopMovieOfMonth();

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取每周的电影排行榜(包含动漫)
    *@param:
    *@author:ggh
    *@updateTime: 2020/6/18 18:00
    **/
    @RequestMapping("/recommend/week/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfWeek(){

        ResultEntity resultEntity = recommendService.getTopMovieOfWeek();

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取指定类别的电影排行榜
    *@param: category:类别
    *@author:ggh
    *@updateTime: 2020/6/18 18:01
    **/
    @RequestMapping("/recommend/category/movies/{category}")
    @ResponseBody
    public ResultEntity getTopMoviesOfCategory(@PathVariable("category") String category){

        ResultEntity resultEntity = recommendService.getTopMoviesOfCategory(category);

        return resultEntity;
    }

    /**
    *@title:
    *@description: 获取用户的电影推荐列表
    *@param: uid:用户id
    *@author:ggh
    *@updateTime: 2020/6/18 18:02
    **/
    @RequestMapping("/recommend/user/movies/{uid}")
    @ResponseBody
    public ResultEntity getUserRecommendationDao(@PathVariable("uid") Long uid){

        ResultEntity resultEntity = recommendService.getUserRecommendationDao(uid);

        return resultEntity;
    }

}
