package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/18 21:45
 */
@Component
@FeignClient(value = "database-server")
public interface DatabaseService {

    /**
     *@title:
     *@description:获取所有电影数据
     *@param:
     *@author:ggh
     *@updateTime: 2020/5/18 21:48
     **/
    @RequestMapping("/get/all")
    @ResponseBody
    public ResultEntity<List<MovieInfo>> getAllMovieInfo();

}
