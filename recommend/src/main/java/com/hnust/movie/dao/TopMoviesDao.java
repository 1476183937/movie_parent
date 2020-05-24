package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.TopMovieOfWeek;
import com.hnust.movie.entity.recommender.TopMovies;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface TopMoviesDao {

    void save(TopMovies topMovies);

    void update(TopMovies topMovies);

    List<TopMovies> findAll();

    void delete(String id);

    TopMovies findLatest();

}
