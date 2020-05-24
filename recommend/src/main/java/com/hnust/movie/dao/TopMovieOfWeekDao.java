package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.TopMovieOfMonth;
import com.hnust.movie.entity.recommender.TopMovieOfWeek;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface TopMovieOfWeekDao {

    void save(TopMovieOfWeek topMovieOfWeek);

    void update(TopMovieOfWeek topMovieOfWeek);

    List<TopMovieOfWeek> findAll();

    void delete(String id);

    TopMovieOfWeek findLatest();

}
