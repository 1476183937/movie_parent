package com.hnust.movie.service;

import com.hnust.movie.entity.recommender.*;
import com.hnust.movie.entity.vo.ResultEntity;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 17:53
 */
public interface RecommendService {

    ResultEntity<TopMovies> getTopMovies();

    ResultEntity<TopComics> getTopComics();

    ResultEntity<SimilarMovieRecommendation> getSimilarMovieRecommendation(Long mid);

    ResultEntity<TopMovieOfMonth> getTopMovieOfMonth();

    ResultEntity<TopMovieOfWeek> getTopMovieOfWeek();

    ResultEntity<List<TopMoviesOfCategory>> getTopMoviesOfCategory(String categories);

    ResultEntity<UserRecommendation> getUserRecommendationDao(Long uid);

    ResultEntity<MultipleRankings> getMultipleRankings();

}
