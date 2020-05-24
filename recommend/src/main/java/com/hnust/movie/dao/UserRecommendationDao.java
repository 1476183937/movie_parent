package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.TopMovies;
import com.hnust.movie.entity.recommender.TopMoviesOfCategory;
import com.hnust.movie.entity.recommender.UserRecommendation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
public interface UserRecommendationDao {

    void save(UserRecommendation userRecommendation);

    void update(UserRecommendation userRecommendation);

    List<UserRecommendation> findAll();

    void delete(String id);

    UserRecommendation findLatestByUid(Long uid);

}
