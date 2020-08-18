package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.MovieListVO;
import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:操作缓存的服务
 * @Author: ggh
 * @Date: 2020/5/18 10:00
 */
@Component
@FeignClient(value = "cache-service")
public interface CacheService {

    /**
     *@title:
     *@description: 根据电影id从缓存中获取数据
     *@author:ggh
     *@updateTime: 2020/5/17 21:21
     **/
    @RequestMapping("/movieInfo/get/{movieId}")
    @ResponseBody
//    public ResultEntity<MovieInfo> getMovieInfoFromCache(@PathVariable("movieId") Long movieId);
    public ResultEntity<MovieInfoInCache> getMovieInfoFromCache(@PathVariable("movieId") Long movieId);


    /**
     *@title:
     *@description: 为顶部导航栏中的动漫标签页获取显示的数据
     *@param:
     *@author:ggh
     *@updateTime: 2020/5/22 15:50
     **/
    @RequestMapping("/cache/comic/list")
    @ResponseBody
    public ResultEntity getMultipleComicList();

    /**
     *@title:
     *@description: 为顶部导航栏中的电影标签页获取显示的数据
     *@param:
     *@author:ggh
     *@updateTime: 2020/5/22 15:52
     **/
    @RequestMapping("/cache/movie/list")
    @ResponseBody
    public ResultEntity<MovieListVO> getMultipleMoviesList();

}
