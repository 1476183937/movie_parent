package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.TopComics;
import com.hnust.movie.entity.recommender.TopMovieOfMonth;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface TopMovieOfMonthDao {

    void save(TopMovieOfMonth topMovieOfMonth);

    void update(TopMovieOfMonth topMovieOfMonth);

    List<TopMovieOfMonth> findAll();

    void delete(String id);

    TopMovieOfMonth findLatest();

}
