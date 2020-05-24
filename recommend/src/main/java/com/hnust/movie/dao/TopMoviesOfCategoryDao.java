package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.TopMovies;
import com.hnust.movie.entity.recommender.TopMoviesOfCategory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface TopMoviesOfCategoryDao {

    void save(TopMoviesOfCategory topMoviesOfCategory);

    void update(TopMoviesOfCategory topMoviesOfCategory);

    List<TopMoviesOfCategory> findAll();

    void delete(String id);

    TopMoviesOfCategory findLatestByCategory(String category);

}
