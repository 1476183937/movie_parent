package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/22 11:05
 */
public interface CacheService {

    ResultEntity getMultipleMoviesList();

    ResultEntity getMultipleComicList();

    ResultEntity getInfo(String key);

}
