package com.hnust.movie.controller;

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

    @RequestMapping("/recommend/top/movies")
    @ResponseBody
    public ResultEntity getTopMovies(){

        ResultEntity resultEntity = recommendService.getTopMovies();

        return resultEntity;
    }

    @RequestMapping("/recommend/top/comics")
    @ResponseBody
    public ResultEntity getTopComics(){

        ResultEntity resultEntity = recommendService.getTopComics();

        return resultEntity;
    }

    @RequestMapping("/recommend/similar/movies/{mid}")
    @ResponseBody
    public ResultEntity getSimilarMovieRecommendation(@PathVariable("mid") Long mid){

        ResultEntity resultEntity = recommendService.getSimilarMovieRecommendation(mid);

        return resultEntity;
    }

    @RequestMapping("/recommend/month/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfMonth(){

        ResultEntity resultEntity = recommendService.getTopMovieOfMonth();

        return resultEntity;
    }

    @RequestMapping("/recommend/week/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfWeek(){

        ResultEntity resultEntity = recommendService.getTopMovieOfWeek();

        return resultEntity;
    }

    @RequestMapping("/recommend/category/movies/{category}")
    @ResponseBody
    public ResultEntity getTopMoviesOfCategory(@PathVariable("category") String category){

        ResultEntity resultEntity = recommendService.getTopMoviesOfCategory(category);

        return resultEntity;
    }

    @RequestMapping("/recommend/user/movies/{uid}")
    @ResponseBody
    public ResultEntity getUserRecommendationDao(@PathVariable("uid") Long uid){

        ResultEntity resultEntity = recommendService.getUserRecommendationDao(uid);

        return resultEntity;
    }

}
