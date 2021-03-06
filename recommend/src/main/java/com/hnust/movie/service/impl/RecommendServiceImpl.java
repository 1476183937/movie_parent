package com.hnust.movie.service.impl;

import com.hnust.movie.dao.*;
import com.hnust.movie.entity.recommender.*;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 17:53
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private SimilarMovieRecommendationDao similarMovieRecommendationDao;

    @Autowired
    private TopComicsDao topComicsDao;

    @Autowired
    private TopMoviesDao topMoviesDao;

    @Autowired
    private TopMovieOfMonthDao topMovieOfMonthDao;

    @Autowired
    private TopMovieOfWeekDao topMovieOfWeekDao;

    @Autowired
    private TopMoviesOfCategoryDao topMoviesOfCategoryDao;


    @Autowired
    private UserRecommendationDao userRecommendationDao;

    @Autowired
    private MultipleRankingDao multipleRankingDao;

    /**
    *@title:
    *@description: 获取电影(包含动漫)排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/23 18:00
    **/
    @Override
    public ResultEntity<TopMovies> getTopMovies() {

        TopMovies topMovies = topMoviesDao.findLatest();

        if (topMovies != null){
            return ResultEntity.successWithData(topMovies);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取最新的动漫排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/23 18:02
    **/
    @Override
    public ResultEntity<TopComics> getTopComics() {

        TopComics topComics = topComicsDao.findLatest();

        if (topComics != null){
            return ResultEntity.successWithData(topComics);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 根据电影id获取相似电影
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/23 18:03
    **/
    @Override
    public ResultEntity<SimilarMovieRecommendation> getSimilarMovieRecommendation(Long mid) {

        SimilarMovieRecommendation movieRecommendation = similarMovieRecommendationDao.findLatestByMid(mid);

        if (movieRecommendation != null){
            return ResultEntity.successWithData(movieRecommendation);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取每月的电影(包含动漫)排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/23 18:05
    **/
    @Override
    public ResultEntity<TopMovieOfMonth> getTopMovieOfMonth() {

        TopMovieOfMonth topMovieOfMonth = topMovieOfMonthDao.findLatest();

        if (topMovieOfMonth != null){
            return ResultEntity.successWithData(topMovieOfMonth);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取每周的电影(包含动漫)排行榜
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/23 18:06
    **/
    @Override
    public ResultEntity<TopMovieOfWeek> getTopMovieOfWeek() {

        TopMovieOfWeek movieOfWeek = topMovieOfWeekDao.findLatest();

        if (movieOfWeek != null){
            return ResultEntity.successWithData(movieOfWeek);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取指定类别的电影(包含动漫)排行榜
    *@param: category
    *@author:ggh
    *@updateTime: 2020/5/23 18:07
    **/
    @Override
    public ResultEntity<List<TopMoviesOfCategory>> getTopMoviesOfCategory(String categories) {

        List<TopMoviesOfCategory> latestByCategory = topMoviesOfCategoryDao.findLatestByCategories(categories);

        if (latestByCategory != null){
            return ResultEntity.successWithData(latestByCategory);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取指定用户的电影(包含动漫)推荐数据
    *@param: uid
    *@author:ggh
    *@updateTime: 2020/5/23 18:08
    **/
    @Override
    public ResultEntity<UserRecommendation> getUserRecommendationDao(Long uid) {

        UserRecommendation userRecommendation = userRecommendationDao.findLatestByUid(uid);

        if (userRecommendation != null){
            return ResultEntity.successWithData(userRecommendation);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 获取最新的复合排行榜数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/8/13 11:13
    **/
    public ResultEntity<MultipleRankings> getMultipleRankings(){

        MultipleRankings multipleRankings = multipleRankingDao.findLatest();
        if (multipleRankings.getMultipleRankings().size() > 0){

            return ResultEntity.successWithData(multipleRankings);
        }else{
            return ResultEntity.failed("get multipleRanking failed");
        }

    }
}
