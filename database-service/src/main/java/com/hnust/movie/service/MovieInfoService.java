package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ComicListVO;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.MovieListVO;
import com.hnust.movie.entity.vo.ResultEntity;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:50
 */
public interface MovieInfoService {


    //为首页获取最新热播电影列表,is_comic表示是获取电影还是动漫
//    ResultEntity<List<MovieInfo>> getMovieInfoForIndexPage(int is_comic);
    ResultEntity<List<MovieInfoInCache>> getMovieInfoForIndexPage(int is_comic);

    //根据电影或动漫id获取详情信息
//    ResultEntity<MovieInfo> getDetailByMovieId(Long movieId);
    ResultEntity<MovieInfoInCache> getDetailByMovieId(Long movieId);

    //获取评分较高的电影信息，可用于用户未登陆时或有的电影还未生产推荐列表，可以暂时作为获取推荐列表使用
    List<MovieInfoInCache> getTopRatingMovies(int start,int size);


    List<MovieInfo> getAll();

    ResultEntity<MovieListVO> getMovieInfoByCategory();

    ResultEntity<ComicListVO> getComicByCategpry();

    ResultEntity<List<MovieInfoInCache>> getLatestMovies(int size);


}
