package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ComicListVO;
import com.hnust.movie.entity.vo.MovieListVO;
import com.hnust.movie.entity.vo.ResultEntity;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:50
 */
public interface MovieInfoService {

//    MovieInfo test();

    //为首页获取最新热播电影列表,is_comic表示是获取电影还是动漫
    ResultEntity<List<MovieInfo>> getMovieInfoForIndexPage(int is_comic);

    //根据电影或动漫id获取详情信息
    ResultEntity<MovieInfo> getDetailByMovieId(Long movieId);

    List<MovieInfo> getAll();

    ResultEntity<MovieListVO> getMovieInfoByCategory();

    ResultEntity<ComicListVO> getComicByCategpry();


}
