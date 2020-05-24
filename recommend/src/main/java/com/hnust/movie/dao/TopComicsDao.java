package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.recommender.TopComics;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface TopComicsDao {

    void save(TopComics topComics);

    void update(TopComics topComics);

    List<TopComics> findAll();

    void delete(String id);

    TopComics findLatest();

}
