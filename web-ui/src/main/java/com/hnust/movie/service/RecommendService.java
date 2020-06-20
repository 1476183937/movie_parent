package com.hnust.movie.service;

import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 18:21
 */
@Component
@FeignClient(value = "recommender-server")
public interface RecommendService {

    @RequestMapping("/recommend/top/movies")
    @ResponseBody
    public ResultEntity getTopMovies();

    @RequestMapping("/recommend/top/comics")
    @ResponseBody
    public ResultEntity getTopComics();

    @RequestMapping("/recommend/similar/movies/{mid}")
    @ResponseBody
    public ResultEntity<SimilarMovieRecommendation> getSimilarMovieRecommendation(@PathVariable("mid") Long mid);

    @RequestMapping("/recommend/month/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfMonth();

    @RequestMapping("/recommend/week/movies")
    @ResponseBody
    public ResultEntity getTopMovieOfWeek();

    @RequestMapping("/recommend/category/movies/{category}")
    @ResponseBody
    public ResultEntity getTopMoviesOfCategory(@PathVariable("category") String category);

    @RequestMapping("/recommend/user/movies/{uid}")
    @ResponseBody
    public ResultEntity getUserRecommendationDao(@PathVariable("uid") Long uid);

}
