package com.hnust.movie.service;

import com.hnust.movie.entity.po.*;
import com.hnust.movie.entity.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 17:15
 */

@Component
@FeignClient(value = "database-server")
public interface DatabaseService {

    //获取评分较高的电影信息，可用于用户未登陆时或有的电影还未生产推荐列表，可以暂时作为获取推荐列表使用
    @RequestMapping("/db/get/top/rating/movies")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getTopRatingMovies(@RequestParam("start") int start,@RequestParam("size") int size);

        @RequestMapping("/db/index/{is_comic}")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getMovieInfoForIndexPage(@PathVariable("is_comic") int is_comic);

    /**
     *@title:
     *@description: 用户注册
     *@param: userInfo
     *@author:ggh
     *@updateTime: 2020/8/19 9:45
     **/
    @PostMapping("/db/registy")
    @ResponseBody
    public ResultEntity registy(@RequestBody UserInfo userInfo);

    /**
    *@title:
    *@description: 检查指定用户名是否存在
    *@param: userName
    *@author:ggh
    *@updateTime: 2020/8/19 10:21
    **/
    @RequestMapping("/db/exist/username")
    @ResponseBody
    public ResultEntity existUserName(@RequestParam("username") String userName);

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
                                                       @RequestParam("mid") Long mid);

    /**
     *@title:
     *@description: 删除收藏记录
     *@author:ggh
     *@updateTime: 2020/5/29 11:32
     **/
    @RequestMapping("/db/delete")
    @ResponseBody
    public ResultEntity deleteCollection(@RequestParam("collectionId") String collectionId);

    /**
     *@title:
     *@description: 更新收藏记录
     *@author:ggh
     *@updateTime: 2020/5/29 11:32
     **/
    @RequestMapping(value = "/db/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity updateCollection(@RequestBody UserCollection userCollection);

    /**
     *@title:
     *@description: 查询用户收藏记录
     *@param: uid:用户uid
     *@author:ggh
     *@updateTime: 2020/5/28 14:10
     **/
    @RequestMapping("/db/get/collection")
    @ResponseBody
    public ResultEntity<List<UserCollectionVO>> getCollections(@RequestParam("uid") Long uid);

    /**
     *@title:
     *@description: 添加用户收藏记录
     *@param: userCollection
     *@author:ggh
     *@updateTime: 2020/5/28 14:10
     **/
    @RequestMapping(value = "/db/add/collection",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addCollection(@RequestBody UserCollection userCollection);

    /**
     *@title:
     *@description: 查询用户的观看历史
     *@param: uid ：用户id
     *@author:ggh
     *@updateTime: 2020/5/28 13:43
     **/
    @RequestMapping("/db/get/history")
    @ResponseBody
    public ResultEntity<List<ScanHistory>> getHistory(@RequestParam("uid") Long uid);

    /**
     *@title:
     *@description: 添加用户观看历史
     *@param: scanHistory
     *@author:ggh
     *@updateTime: 2020/5/28 13:44
     **/
    @RequestMapping(value = "/db/add/history",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addHistory(@RequestBody ScanHistory scanHistory);

    /**
     *@title:
     *@description: 删除用户观看历史记录
     *@param: scanId
     *@author:ggh
     *@updateTime: 2020/5/29 11:40
     **/
    @RequestMapping("/db/delete/history")
    @ResponseBody
    public ResultEntity deleteHistory(@RequestParam("scanId") String scanId);

    /**
    *@title:
    *@description: 根据电影id获取电影的详情数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/db/detailInfo/{movieId}")
    @ResponseBody
//    public ResultEntity<MovieInfo> getDetailInfo(@PathVariable("movieId") Long movieId);
    public ResultEntity<MovieInfoInCache> getDetailInfo(@PathVariable("movieId") Long movieId);

    /**
    *@title:
    *@description: 根据电影id获取相关的评论数据
    *@author:ggh
    *@updateTime: 2020/5/14 18:32
    **/
    @RequestMapping("/db/detailInfo/comment/{movieId}")
    @ResponseBody
    public ResultEntity<List<CommentVO>> getCommentByMid(@PathVariable("movieId") Long movieId,
                                                         @RequestParam(value = "startPage",required = false,defaultValue = "1") int startPage);


    /**
    *@title:
    *@description: 添加评论
    *@author:ggh
    *@updateTime: 2020/5/15 18:20
    **/
    @PostMapping("/comment/publishComment")
    @ResponseBody
    public ResultEntity addComment(@RequestBody Comment comment);

    /**
     *@title:
     *@description: 获取所有分类信息，包含分类、地区、年份等
     *@param:
     *@author:ggh
     *@updateTime: 2020/5/20 11:05
     **/
    @RequestMapping("/db/category/all")
    @ResponseBody
    public ResultEntity<CategorySearchVO> getAllCategoriesInfo();

    /**
     *@title:
     *@description: 添加评分
     *@param: rating
     *@author:ggh
     *@updateTime: 2020/5/24 12:23
     **/
    @RequestMapping("/db/add/rating")
    @ResponseBody
    public ResultEntity addRating(@RequestBody Rating rating);

    /**
     *@title:
     *@description:
     *@param: uid
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/24 12:20
     **/
    @RequestMapping("/db/movie/rating")
    @ResponseBody
    public ResultEntity isScored(@RequestParam("uid") Long uid, @RequestParam("mid") Long mid);

    @RequestMapping("/db/get/latest/movies/{size}")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getLatestAllMovies(@PathVariable("size") int size);

}
