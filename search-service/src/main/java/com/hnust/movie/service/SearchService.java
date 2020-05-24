package com.hnust.movie.service;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import io.searchbox.client.JestClient;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/19 10:04
 */
public interface SearchService {

    //导入数据到es
    boolean importDataToEs(JestClient jestClient, DatabaseService databaseService);

    //根据关键字查询数据
    ResultEntity<SearchResultVO> getMovieInfoByKw(JestClient jestClient, String kw, int from, int size);

    //根据多个类别查询
    public ResultEntity<SearchResultVO> getMovieInfoByCategories(int pcid,
                                                                 String category,
                                                                 String location,
                                                                 String year,
                                                                 String letter,
                                                                 String sort,
                                                                 int from,
                                                                 int size);

}
