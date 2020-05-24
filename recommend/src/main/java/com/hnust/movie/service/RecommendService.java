package com.hnust.movie.service;

import com.hnust.movie.entity.vo.ResultEntity;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 17:53
 */
public interface RecommendService {

    ResultEntity getTopMovies();

    ResultEntity getTopComics();

    ResultEntity getSimilarMovieRecommendation(Long mid);

    ResultEntity getTopMovieOfMonth();

    ResultEntity getTopMovieOfWeek();

    ResultEntity getTopMoviesOfCategory(String category);

    ResultEntity getUserRecommendationDao(Long uid);

}
