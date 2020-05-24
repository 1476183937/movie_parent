package com.hnust.movie.controller;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/18 21:32
 */
@Controller
public class SearchController {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private SearchService searchService;


    /**
    *@title:
    *@description: 从数据库导入数据到es
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/18 21:39
    **/
    @RequestMapping("/es/import")
    public void importDataToEs(){

       searchService.importDataToEs(jestClient, databaseService);

    }


    /**
    *@title:
    *@description: 根据关键字去es查询
    *@param: kw ：关键字
    *@param: from ：查询起始位置
    *@param: size ：结果记录条数
    *@author:ggh
    *@updateTime: 2020/5/19 11:29
    **/
    @RequestMapping("/es/get")
    @ResponseBody
    public ResultEntity<SearchResultVO> getMovieInfoByKw(@RequestParam("kw") String kw,
                                                          @RequestParam(value = "from",defaultValue = "0") int from,
                                                          @RequestParam(value = "size",defaultValue = "10") int size){

        ResultEntity<SearchResultVO> movieInfoByKw = searchService.getMovieInfoByKw(jestClient, kw, from, size);

        return movieInfoByKw;
    }

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
                                                                 @RequestParam(value = "from",required = false,defaultValue = "1") int from,
                                                                 @RequestParam(value = "size",required = false,defaultValue = "30") int size){

        ResultEntity<SearchResultVO> movieInfoByCategories = searchService.getMovieInfoByCategories(pcid, category, location, year, letter, sort, from, size);

        return movieInfoByCategories;
    }


}
