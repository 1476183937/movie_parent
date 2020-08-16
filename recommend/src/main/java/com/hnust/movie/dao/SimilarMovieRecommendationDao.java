package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface SimilarMovieRecommendationDao {

    void save(SimilarMovieRecommendation similarMovieRecommendation);

    void update(SimilarMovieRecommendation similarMovieRecommendation);

    List<SimilarMovieRecommendation> findAll();

    //根据mid找出与该电影最新的相似度电影数据
    SimilarMovieRecommendation findLatestByMid(Long mid);

    void delete(String id);

}
