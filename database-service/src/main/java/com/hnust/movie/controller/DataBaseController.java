package com.hnust.movie.controller;

import com.hnust.movie.entity.po.*;
import com.hnust.movie.entity.vo.*;
import com.hnust.movie.mapper.MovieInfoMapper;
import com.hnust.movie.service.*;
import javafx.geometry.Pos;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:44
 */
@Controller
public class DataBaseController {

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private YearService yearService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ScanHistoryService scanHistoryService;

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private MovieInfoMapper movieInfoMapper;

//    导入数据测试用
    @RequestMapping("/test")
    public void insertTest(){

        List<MovieInfo> all = movieInfoMapper.getAll();
        int cnt = 1;
        for (MovieInfo movieInfo : all) {
            movieInfoMapper.insertTest(movieInfo.getMid()+1,movieInfo.getMid(),movieInfo.getPlayUrl());
            System.out.println("第 "+ cnt++ +" 条"+": "+movieInfo.getMid());
        }

    }


    //获取评分较高的电影信息，可用于用户未登陆时或有的电影还未生产推荐列表，可以暂时作为获取推荐列表使用
    @RequestMapping("/db/get/top/rating/movies")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getTopRatingMovies(@RequestParam("start") int start,@RequestParam("size") int size){

        List<MovieInfoInCache> topRatingMovies = movieInfoService.getTopRatingMovies(start, size);

        if (topRatingMovies.size() >0){
            return ResultEntity.successWithData(topRatingMovies);
        }else{
            return ResultEntity.failed("get top rating movies fail");
        }
    }


    /**
    *@title:
    *@description: 查询用户收藏记录
    *@param: uid:用户uid
    *@author:ggh
    *@updateTime: 2020/5/28 14:10
    **/
    @RequestMapping("/db/get/collection")
    @ResponseBody
    public ResultEntity<List<UserCollectionVO>> getCollections(@RequestParam("uid") Long uid){

        List<UserCollectionVO> userCollections = userCollectionService.getCollection(uid);

        if (userCollections != null){
            return ResultEntity.successWithData(userCollections);
        }else{
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 添加用户收藏记录
    *@param: userCollection
    *@author:ggh
    *@updateTime: 2020/5/28 14:10
    **/
    @RequestMapping(value = "/db/add/collection",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addCollection(@RequestBody UserCollection userCollection){

        int result = userCollectionService.addCollection(userCollection);
        if (result > 0){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("add failed");
        }

    }

    /**
    *@title:
    *@description: 根据用户id和电影id查询相应的收藏记录
    *@param: uid
    *@param: mid
    *@author:ggh
    *@updateTime: 2020/5/29 11:32
    **/
    @RequestMapping("/db/get/collection/by")
    @ResponseBody
    public ResultEntity<UserCollection> getCollectionByUidAndMid(@RequestParam("uid") Long uid,
                                                       @RequestParam("mid") Long mid){

        UserCollection userCollection = userCollectionService.getByUidAndMid(uid, mid);

        if (userCollection != null){
            return ResultEntity.successWithData(userCollection);
        }else{
            return ResultEntity.failed("get failed");
        }

    }


    /**
     *@title:
     *@description: 删除收藏记录
     *@author:ggh
     *@updateTime: 2020/5/29 11:32
     **/
    @RequestMapping("/db/delete")
    @ResponseBody
    public ResultEntity deleteCollection(@RequestParam("collectionId") String collectionId){

        int result = userCollectionService.deleteCollection(collectionId);

        if (result >0){
            return ResultEntity.successNoData();
        }

        return ResultEntity.failed("delete failed");
    }

    /**
     *@title:
     *@description: 更新收藏记录
     *@author:ggh
     *@updateTime: 2020/5/29 11:32
     **/
    @RequestMapping(value = "/db/update/collection",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity updateCollection(@RequestBody UserCollection userCollection){

        int result = userCollectionService.updateCollection(userCollection);
        if (result > 0){
            return ResultEntity.successNoData();
        }

        return ResultEntity.failed("update failed");
    }


    /**
    *@title:
    *@description: 查询用户的观看历史
    *@param: uid ：用户id
    *@author:ggh
    *@updateTime: 2020/5/28 13:43
    **/
    @RequestMapping("/db/get/history")
    @ResponseBody
    public ResultEntity<List<ScanHistory>> getHistory(@RequestParam("uid") Long uid){

        List<ScanHistory> scanHistories = scanHistoryService.getHistory(uid);

        if (scanHistories != null){
            return ResultEntity.successWithData(scanHistories);
        }else {
            return ResultEntity.failed("get failed");
        }

    }

    /**
    *@title:
    *@description: 添加用户观看历史
    *@param: scanHistory
    *@author:ggh
    *@updateTime: 2020/5/28 13:44
    **/
    @RequestMapping(value = "/db/add/history",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addHistory(@RequestBody ScanHistory scanHistory){

        int result = scanHistoryService.addHistory(scanHistory);
        if (result >0){
            return ResultEntity.successNoData();
        }else{
            return ResultEntity.failed("add failed");
        }

    }

    /**
    *@title:
    *@description: 删除用户观看历史记录
    *@param: scanId
    *@author:ggh
    *@updateTime: 2020/5/29 11:40
    **/
    @RequestMapping("/db/delete/history")
    @ResponseBody
    public ResultEntity deleteHistory(@RequestParam("scanId") String scanId){

        int result = scanHistoryService.deleteHistory(scanId);
        if (result > 0){
            return ResultEntity.successNoData();
        }

        return ResultEntity.failed("delete failed");
    }


    @RequestMapping("/db/index/{is_comic}")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getMovieInfoForIndexPage(@PathVariable(value = "is_comic") int is_comic){

        return movieInfoService.getMovieInfoForIndexPage(is_comic);

    }

    /**
    *@title:
    *@description:判断每个用户是否对某个电影评分过
    *@param: uid
    *@param: mid
    *@author:ggh
    *@updateTime: 2020/5/24 12:20
    **/
    @RequestMapping("/db/movie/rating")
    @ResponseBody
    public ResultEntity isScored(@RequestParam("uid") Long uid, @RequestParam("mid") Long mid){

        Rating rating = ratingService.isScored(uid, mid);
        if (rating == null){
            return ResultEntity.failed("get failed");
        }else{
            return ResultEntity.successWithData(rating);
        }

    }


    /**
    *@title:
    *@description: 添加评分
    *@param: rating
    *@author:ggh
    *@updateTime: 2020/5/24 12:23
    **/
    @RequestMapping("/db/add/rating")
    @ResponseBody
    public ResultEntity addRating(@RequestBody Rating rating){

        int result = ratingService.addRating(rating);

        if (result > 0){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("insert failed");
        }

    }


    /**
    *@title: 
    *@description: 获取所有分类信息，包含分类、地区、年份等
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/20 11:05
    **/
    @RequestMapping("/db/category/all")
    @ResponseBody
    public ResultEntity<CategorySearchVO> getAllCategoriesInfo(){

        //所有类别
        List<Category> categoryList = categoryService.getAll();

        //所有地区
        List<Location> locationList = locationService.getAll();

        //所有年份
        List<Year> yearList = yearService.getAll();

        CategorySearchVO categorySearchVO = new CategorySearchVO();
        categorySearchVO.setCategoryList(categoryList);
        categorySearchVO.setLocationList(locationList);
        categorySearchVO.setYearList(yearList);

        return ResultEntity.successWithData(categorySearchVO);
    }


    /**
    *@title:
    *@description: 根据id获取电影详情数据
    *@author:ggh
    *@updateTime: 2020/5/14 10:23
    **/
    @RequestMapping("/db/detailInfo/{movieId}")
    @ResponseBody
//    public ResultEntity<MovieInfo> getDetailInfo(@PathVariable("movieId") Long movieId){
    public ResultEntity<MovieInfoInCache> getDetailInfo(@PathVariable("movieId") Long movieId){

//        ResultEntity<MovieInfo> resultEntity = movieInfoService.getDetailByMovieId(movieId);
        ResultEntity<MovieInfoInCache> resultEntity = movieInfoService.getDetailByMovieId(movieId);

        return resultEntity;
    }


    /**
    *@title:
    *@description: 根据电影id获取相关的评论数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:29
    **/
    @RequestMapping("/db/detailInfo/comment/{movieId}")
    @ResponseBody
    public ResultEntity<List<CommentVO>> getCommentByMid(
            @PathVariable("movieId") Long movieId,
            @RequestParam(value = "startPage",required = false,defaultValue = "1") int startPage){

//        System.out.println("movieId: "+movieId +"startPage: " + startPage);
        //检查movieId的合法性
        if (StringUtils.isBlank(movieId+"")){
            return ResultEntity.failed("解析错误：movieId");
        }
        List<CommentVO> comments = commentService.selectCommentByMid(movieId, startPage);

        ResultEntity<List<CommentVO>> listResultEntity = null;
        if (comments != null){
            listResultEntity = ResultEntity.successWithData(comments);
            return listResultEntity;
        }else {
            return ResultEntity.failed("get comments fialed!!!");
        }


    }


    /**
    *@title:
    *@description: 添加评论
    *@author:ggh
    *@updateTime: 2020/5/15 18:15
    **/
    @PostMapping("/comment/publishComment")
    @ResponseBody
    public ResultEntity addComment(@RequestBody Comment comment){

        int result = commentService.insertComment(comment);
        if (result>0){ //添加成功
            return ResultEntity.successNoData();
        }else {         //添加失败
            return ResultEntity.failed("publish fail");
        }

    }

    /**
    *@title:
    *@description:获取所有电影数据,不做任何过滤
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/18 21:48
    **/
    @RequestMapping("/get/all")
    @ResponseBody
    public ResultEntity<List<MovieInfo>> getAllMovieInfo(){

        List<MovieInfo> movieInfos = movieInfoService.getAll();

        return ResultEntity.successWithData(movieInfos);
    }


    /**
    *@title:
    *@description: 为导航栏中电影标签页获取电影列表数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 12:00
    **/
    @RequestMapping("/db/movies/list")
    @ResponseBody
    public ResultEntity<MovieListVO> getMovieListByMultipleCategory(){

        ResultEntity<MovieListVO> movieInfoByCategory = movieInfoService.getMovieInfoByCategory();

        if (movieInfoByCategory.getData() != null){
            return movieInfoByCategory;
        }else {
            return ResultEntity.failed("get failed");
        }


    }

    /**
    *@title:
    *@description: 为导航栏中动漫标签页获取动漫列表数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 12:01
    **/
    @RequestMapping("/db/comics/list")
    @ResponseBody
    public ResultEntity<ComicListVO> getComicListByMultipleCategory(){

        ResultEntity<ComicListVO> comicByCategpry = movieInfoService.getComicByCategpry();

        if (comicByCategpry != null){
            return comicByCategpry;
        }else {
            return ResultEntity.failed("get failed");
        }

    }


    @RequestMapping("/login")
    @ResponseBody
    public ResultEntity<UserInfo> login(@RequestBody UserInfo userInfo){

        if (StringUtils.isNotBlank(userInfo.getUsername()) && StringUtils.isNotBlank(userInfo.getPassword())) {

            UserInfo info = userInfoService.login(userInfo);
            if (info != null){
                return ResultEntity.successWithData(info);
            }else {
                return ResultEntity.failed("login fail!");
            }
        }else{
            return ResultEntity.failed("用户名或密码不能为空!");
        }



    }

    @RequestMapping("/db/get/latest/movies/{size}")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getLatestAllMovies(@PathVariable("size") int size){

        ResultEntity<List<MovieInfoInCache>> latestMovies = movieInfoService.getLatestMovies(size);

        return latestMovies;
    }


}
