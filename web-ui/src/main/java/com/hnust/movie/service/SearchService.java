package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/19 11:31
 */
@Component
@FeignClient(value = "search-service")
public interface SearchService {

    /**
    *@title:
    *@description: 根据关键字去es查询
    *@param: kw ：关键字
    *@param: from：查询起始位置
    *@param: size：结果记录条数
    *@author:ggh
    *@updateTime: 2020/5/19 11:32
    **/
    @RequestMapping("/es/get")
    @ResponseBody
    public ResultEntity<SearchResultVO> getMovieInfoByKw(@RequestParam("kw") String kw,
                                                         @RequestParam(value = "from",defaultValue = "0") int from,
                                                         @RequestParam(value = "size",defaultValue = "10") int size);

    /**
     *@title:
     *@description: 根据在多个类别(类别、地区、年份等条件)搜索的页面中得到的多个类别查询数据
     *@param: pcid :父类别->0:电影 1：动漫
     *@param: category :类别
     *@param: location ：地区
     *@param: year：年份
     *@param: letter：字母
     *@param: sort：排序：按更新(releaseDate)、按人气(hotDegree)、按推荐
     *@param: from:查询起始位置
     *@param: size:结果记录条数
     *@author:ggh
     *@updateTime: 2020/5/21 11:52
     **/
    @RequestMapping("/es/categories/get")
    @ResponseBody
    public ResultEntity<SearchResultVO> getMovieInfoByCategories(@RequestParam(value = "pcid",required = false,defaultValue = "0") int pcid,
                                                                 @RequestParam(value = "category",required = false,defaultValue = "全部") String category,
                                                                 @RequestParam(value = "location",required = false,defaultValue = "全部") String location,
                                                                 @RequestParam(value = "year",required = false,defaultValue = "全部") String year,
                                                                 @RequestParam(value = "letter",required = false,defaultValue = "全部") String letter,
                                                                 @RequestParam(value = "sort",required = false,defaultValue = "releaseDate") String sort,
                                                                 @RequestParam(value = "from",required = false,defaultValue = "0") int from,
                                                                 @RequestParam(value = "size",required = false,defaultValue = "30") int size);


}
