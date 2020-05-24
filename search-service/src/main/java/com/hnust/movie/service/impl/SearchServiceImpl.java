package com.hnust.movie.service.impl;

import com.hnust.movie.entity.MovieInfoVO;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/19 10:04
 */
@Service
public class SearchServiceImpl implements SearchService {


    @Autowired
    private JestClient jestClient;

    /**
    *@title:
    *@description: 导入数据到es
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/19 10:17
    **/
    @Override
    public boolean importDataToEs(JestClient jestClient, DatabaseService databaseService) {

        String indexName = "movieinfo_index";
        String typeName = "movieInfo";


        ResultEntity<List<MovieInfo>> allMovieInfo = databaseService.getAllMovieInfo();
        List<MovieInfo> movieInfoList = allMovieInfo.getData();

        Random random = new Random();

        //遍历导入es
        for (MovieInfo info : movieInfoList) {

            if (info.getReleaseDate().length()>4 && info.getReleaseDate()!=null){
                MovieInfoVO movieInfoVO = new MovieInfoVO();
                BeanUtils.copyProperties(info,movieInfoVO);
                try {
                    movieInfoVO.setReleaseDate(Integer.parseInt(info.getReleaseDate().substring(0,4)));
                    movieInfoVO.setHotDegree(random.nextInt(500));
//                    info.setReleaseDate(info.getReleaseDate().substring(0,4));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
//                Index index = new Index.Builder(movieInfoVO).index(indexName).type(typeName).id(info.getMid()+"").build();
                Index index = new Index.Builder(movieInfoVO).index(indexName).type(typeName).id(info.getMid()+"").build();

                try {
                    jestClient.execute(index);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("数据导入失败："+info);
                    continue;
                }
            }else{
                continue;
            }

        }

        return true;
    }

    /**
    *@title:
    *@description: 根据关键字查询数据
    *@param: jestClient：jest客户端
    *@param: kw ：关键字
    *@param: from：起始页数
    *@param: size ：要选取的记录条数
    *@author:ggh
    *@updateTime: 2020/5/19 10:26
    **/
    @Override
    public ResultEntity<SearchResultVO> getMovieInfoByKw(JestClient jestClient, String kw, int from, int size) {

        String indexName = "movieinfo_index";
        String typeName = "movieInfo";

        //返回结果
        SearchResultVO searchResultVO = new SearchResultVO();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("movieName",kw);
        boolQueryBuilder.must(matchQueryBuilder);

        //设置高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("movieName");
        highlightBuilder.preTags("<span style='color:red;font-weight:bold'>");
        highlightBuilder.postTags("</span>");

        searchSourceBuilder.highlighter(highlightBuilder);

        searchSourceBuilder.query(boolQueryBuilder);

//        from = (from -1)*size;

        //设置查询条数
        searchSourceBuilder.from((from -1)*size);
        searchSourceBuilder.size(size);

//        searchSourceBuilder.sort("hotScore", SortOrder.DESC);
        //排序
        searchSourceBuilder.sort("_score", SortOrder.DESC);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(indexName).addType(typeName).build();

        SearchResult searchResult = null;

        try {
            searchResult = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SearchResult.Hit<MovieInfo, Void>> hits = searchResult.getHits(MovieInfo.class);


        ArrayList<MovieInfo> movieInfos = new ArrayList<>();

        //遍历封装电影集合
        for (SearchResult.Hit<MovieInfo, Void> hit : hits) {
            MovieInfo movieInfo = hit.source;

            Map<String, List<String>> highlight = hit.highlight;
            if (highlight != null){
                movieInfo.setMovieName(highlight.get("movieName").get(0));
            }

            movieInfos.add(movieInfo);

        }

        //获取总记录数
        Long total = searchResult.getTotal();

        //计算总页数
        if (from <=0){
            from = 1;
        }
        int totalPage = (int) (total/10);
        if (total % 10 != 0) {

            totalPage++;
        }

        int startPage = 0,endPage=0;
        if (totalPage <10){
            startPage = 1;
            endPage = totalPage;
        }else {

            if (from<10){
                startPage = 1;
                endPage = 9;
            }else {
                //1 2 3 4 5 6 7 8 9 10 11 12 13 14
                if ((totalPage - from) > 4){
                    startPage = from - 4;
                    endPage = from + 4;
                }else {
                    startPage = totalPage -8;
                    endPage = totalPage;
                }
            }
        }

        //设置返回结果的属性
        searchResultVO.setKw(kw);
        searchResultVO.setCurrentPage(from);
        searchResultVO.setNextPage((from + 1)>=totalPage? totalPage:(from + 1));
        searchResultVO.setPrePage((from-1)<=0?1:(from-1));
        searchResultVO.setMovieInfoList(movieInfos);
        searchResultVO.setTotaltPage(totalPage);
        searchResultVO.setTotal(total);
        searchResultVO.setEndPage(endPage);
        searchResultVO.setStartPage(startPage);

        return ResultEntity.successWithData(searchResultVO);
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
    public ResultEntity<SearchResultVO> getMovieInfoByCategories(int pcid,
                                                                 String category,
                                                                 String location,
                                                                 String year,
                                                                 String letter,
                                                                 String sort,
                                                                 int from,
                                                                 int size){
        String indexName = "movieinfo_index";
        String typeName = "movieInfo";

        //返回结果
        SearchResultVO searchResultVO = new SearchResultVO();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //匹配类别、地区、年份
        if (!"全部".equals(category)) {
            MatchQueryBuilder categories = new MatchQueryBuilder("categories", category).operator(Operator.AND);
            boolQueryBuilder.must(categories);
        }

        if (!"全部".equals(location)){
            MatchQueryBuilder location1 = new MatchQueryBuilder("location", location).operator(Operator.AND);

            boolQueryBuilder.must(location1);
        }

        if (!"全部".equals(year)){
            MatchQueryBuilder releaseDate = new MatchQueryBuilder("releaseDate", year).operator(Operator.AND);
            boolQueryBuilder.must(releaseDate);
        }

        //如果父类是电影的话，就不要有类别中含有 "动画" 的数据
        if (pcid == 0){
            MatchQueryBuilder operator = new MatchQueryBuilder("categories", "动画").operator(Operator.AND);
            boolQueryBuilder.mustNot(operator);
        }else{
            //如果父类别是动画，那么类别中就必须含有 "动画"
            MatchQueryBuilder operator = new MatchQueryBuilder("categories", "动画").operator(Operator.AND);

            boolQueryBuilder.must(operator);
        }

        searchSourceBuilder.query(boolQueryBuilder);

        //排序
        searchSourceBuilder.sort(sort, SortOrder.DESC);


        //设置记录条数
//        from = (from -1)*30;
        if (from <=0){
            from = 1;
        }
        searchSourceBuilder.from((from -1)*30);
        searchSourceBuilder.size(size);

//        System.out.println(searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(indexName).addType(typeName).build();

        SearchResult searchResult = null;
        try {
            searchResult = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SearchResult.Hit<MovieInfo, Void>> hits = searchResult.getHits(MovieInfo.class);

        ArrayList<MovieInfo> movieInfos = new ArrayList<>();
        for (SearchResult.Hit<MovieInfo, Void> hit : hits) {
            MovieInfo movieInfo = hit.source;

            movieInfos.add(movieInfo);
        }


        //获取总记录数
        Long total = searchResult.getTotal();

        //计算总页数
        int totalPage = (int) (total/30);
        if (total % 30 != 0) {

            totalPage++;
        }

        int startPage = 0,endPage=0;
        if (totalPage <10){
            startPage = 1;
            endPage = totalPage;
        }else {

            if (from<10){
                startPage = 1;
                endPage = 9;
            }else {
                //1 2 3 4 5 6 7 8 9 10 11 12 13 14
                if ((totalPage - from) > 4){
                    startPage = from - 4;
                    endPage = from + 4;
                }else {
                    startPage = totalPage -8;
                    endPage = totalPage;
                }
            }
        }


        //设置返回结果的属性
        searchResultVO.setCurrentPage(from);
        searchResultVO.setNextPage((from + 1)>=totalPage? totalPage:(from + 1));
        searchResultVO.setPrePage((from-1)<=0?1:(from-1));
        searchResultVO.setMovieInfoList(movieInfos);
        searchResultVO.setTotaltPage(totalPage);
        searchResultVO.setTotal(total);
        searchResultVO.setStartPage(startPage);
        searchResultVO.setEndPage(endPage);

        return ResultEntity.successWithData(searchResultVO);

    }

}
