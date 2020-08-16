package com.hnust.movie.dao;

import com.hnust.movie.entity.recommender.MultipleRanking;
import com.hnust.movie.entity.recommender.MultipleRankings;
import com.hnust.movie.entity.recommender.TopComics;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/8/13 10:51
 */
public interface MultipleRankingDao {

    void save(MultipleRanking multipleRanking);

    void update(MultipleRanking multipleRanking);

    List<MultipleRanking> findAll();

    void delete(String date,String category);

    MultipleRankings findLatest();

}
