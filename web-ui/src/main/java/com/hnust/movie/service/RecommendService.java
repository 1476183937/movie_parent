package com.hnust.movie.service;

import com.hnust.movie.entity.recommender.*;
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
    public ResultEntity<TopMovies> getTopMovies();

    @RequestMapping("/recommend/top/comics")
    @ResponseBody
    public ResultEntity<TopComics> getTopComics();

    @RequestMapping("/recommend/similar/movies/{mid}")
    @ResponseBody
    public ResultEntity<SimilarMovieRecommendation> getSimilarMovieRecommendation(@PathVariable("mid") Long mid);

    @RequestMapping("/recommend/month/movies")
    @ResponseBody
    public ResultEntity<TopMovieOfMonth> getTopMovieOfMonth();

    @RequestMapping("/recommend/week/movies")
    @ResponseBody
    public ResultEntity<TopMovieOfWeek> getTopMovieOfWeek();

    @RequestMapping("/recommend/category/movies/{category}")
    @ResponseBody
    public ResultEntity<TopMoviesOfCategory> getTopMoviesOfCategory(@PathVariable("category") String category);

    @RequestMapping("/recommend/user/movies/{uid}")
    @ResponseBody
    public ResultEntity<UserRecommendation> getUserRecommendationDao(@PathVariable("uid") Long uid);

    @RequestMapping("/recommend/multiple-ranking")
    @ResponseBody
    public ResultEntity<MultipleRankings> getLatestMultipleRankings();

}
