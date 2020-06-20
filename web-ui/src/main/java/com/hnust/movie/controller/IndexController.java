package com.hnust.movie.controller;

import com.hnust.movie.annotation.LoginRequired;
import com.hnust.movie.common.CommonUtil;
import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.recommender.MovieInMongodb;
import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.recommender.TopComics;
import com.hnust.movie.entity.recommender.TopMovies;
import com.hnust.movie.entity.vo.*;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.RecommendService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Title:首页的controller
 * @Author: ggh
 * @Date: 2020/5/12 21:59
 */
@Controller
public class IndexController {

    //数据库服务
    @Autowired
    private DatabaseService databaseService;

    //缓存服务
    @Autowired
    private CacheService cacheService;

    @Autowired
    private RecommendService recommendService;


    /**
    *@title:
    *@description: 用于生产首页静态页面
    *@param: modelMap
    *@author:ggh
    *@updateTime: 2020/5/18 11:58
    **/
    @RequestMapping(value = {"/index_bak"})
    public String index_bak(ModelMap modelMap){

        ResultEntity<List<MovieInfo>> movieResult = databaseService.getMovieInfoForIndexPage(1);
        List<MovieInfo> movieInfoList = movieResult.getData();
        List<MovieInfo> movieInfoList1 = movieInfoList.subList(0, 11);
        List<MovieInfo> movieInfoList2 = movieInfoList.subList(11, 22);
        List<MovieInfo> movieInfoList3 = movieInfoList.subList(22, 33);

        modelMap.addAttribute("movieLeft01",movieInfoList1.get(0));
        modelMap.addAttribute("movieInfos01",movieInfoList1.subList(1,11));
        modelMap.addAttribute("movieLeft02",movieInfoList2.get(0));
        modelMap.addAttribute("movieInfos02",movieInfoList2.subList(1,11));
        modelMap.addAttribute("movieLeft03",movieInfoList3.get(0));
        modelMap.addAttribute("movieInfos03",movieInfoList3.subList(1,11));

        ResultEntity<List<MovieInfo>> comicEntity = databaseService.getMovieInfoForIndexPage(0);
        List<MovieInfo> comicList = comicEntity.getData();
        List<MovieInfo> comicList1 = comicList.subList(0, 11);
        List<MovieInfo> comicList2 = comicList.subList(11, 22);
        List<MovieInfo> comicList3 = comicList.subList(22, 33);
        modelMap.addAttribute("comicLeft01",comicList1.get(0));
        modelMap.addAttribute("comicInfos01",comicList1.subList(1,11));
        modelMap.addAttribute("comicLeft02",comicList2.get(0));
        modelMap.addAttribute("comicInfos02",comicList2.subList(1,11));
        modelMap.addAttribute("comicLeft03",comicList3.get(0));
        modelMap.addAttribute("comicInfos03",comicList3.subList(1,11));


        ResultEntity<TopMovies> topMovies = recommendService.getTopMovies();

        ResultEntity<TopComics> topComics = recommendService.getTopComics();

        modelMap.addAttribute("topMovies",topMovies);
        modelMap.addAttribute("topComics",topComics);

        return "index_bak";
    }

    /**
    *@title:
    *@description: 获取最新热播电影和动漫用户首页初始化
    *@author:ggh
    *@updateTime: 2020/5/14 10:10
    **/
    @RequestMapping(value = {"/index","/"})
    @LoginRequired(mustLogin = false)
    public String index(ModelMap modelMap){

        return "index";

    }

    /**
    *@title:
    *@description: 点击后进入电影详情页，获取详情数据
    *@param: movieId 电影id
    *@param: startPage 评论起始页
    *@param: modelMap
    *@author:ggh
    *@updateTime: 2020/5/18 10:29
    **/
    @RequestMapping("/index/detail/{movieId}.html")
    @LoginRequired(mustLogin = false)
    public String detail(@PathVariable("movieId") Long movieId,
                         @RequestParam(value = "startPage", required = false,defaultValue = "1") int startPage,
                         ModelMap modelMap){

        int totalPage = 0, count = 0;
        ResultEntity<List<CommentVO>> commentByMid = null;
        ResultEntity<CommentVO> commentVOResultEntity = null;

        ResultEntity<MovieInfo> movieInfoFromCache = cacheService.getMovieInfoFromCache(movieId);
        //如果没有得到数据或数据为空
        if (movieInfoFromCache.getData() == null || "".equals(movieInfoFromCache.getData()) ||
        "NO_DATA".equals(movieInfoFromCache.getData())){
//            modelMap.addAttribute("movieInfo",ResultEntity.failed("NO DATA!!!"));

            //TODO:跳到错误页面（待实现）
            return "error";
        }else {
            //如果得到了数据
            modelMap.addAttribute("movieInfo",movieInfoFromCache);

            //获取评论数据
            commentByMid = databaseService.getCommentByMid(movieId, 1);

            //计算总记录数
            if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 != 0) {
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                totalPage++; //评论总页数
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }else if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 == 0){
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }

            //封装评论返回结果
            CommentVO2 commentVO2 = new CommentVO2();
            commentVO2.setCount(count);
            commentVO2.setCurrentPage(startPage);
            commentVO2.setNextPage((startPage+1)>=totalPage?totalPage:(startPage+1));
            commentVO2.setPrePage((startPage-1)<=1?1:(startPage-1));
            commentVO2.setTotalPage(totalPage);
            commentVO2.setCommentVOList(commentByMid.getData());


            modelMap.addAttribute("commentResult", ResultEntity.successWithData(commentVO2));


            //获取周月的电影排行榜
            ResultEntity topMovieOfMonth = recommendService.getTopMovieOfMonth();
            ResultEntity topMovieOfWeek = recommendService.getTopMovieOfWeek();

            modelMap.addAttribute("topMovieOfMonth",topMovieOfMonth);
            modelMap.addAttribute("topMovieOfWeek",topMovieOfWeek);

            //获取该电影的相似电影
            ResultEntity<SimilarMovieRecommendation> similarMovieRecommendation = recommendService.getSimilarMovieRecommendation(movieId);


            List<MovieInMongodb> simliarMovies = similarMovieRecommendation.getData().getSimliarMovies();
            List<MovieInMongodb> collect = simliarMovies.stream().limit(12).collect(Collectors.toList());
            similarMovieRecommendation.getData().setSimliarMovies(collect);

            //TODO:暂时只显示前12条数据
            modelMap.addAttribute("similarMovie",similarMovieRecommendation);


        }
        return "detail";

    }



    /**
    *@title: 
    *@description: 电影专栏
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/18 12:01
    **/
    @RequestMapping(value = {"/movie-list.html","/movie/list"})
    @LoginRequired(mustLogin = false)
    public String movieList(ModelMap modelMap){


        //获取各类别的电影：最新电影、动作片、喜剧、爱情、科幻、恐怖、剧情、战争片
        ResultEntity<MovieListVO> multipleMoviesList = cacheService.getMultipleMoviesList();

        //获取侧边栏显示的电影排行榜
        ResultEntity<TopMovies> topMovies = recommendService.getTopMovies();

        //获取侧边栏显示的动作片排行榜
        ResultEntity actionTopMovies = recommendService.getTopMoviesOfCategory("动作");

        //获取侧边栏显示的喜剧片排行榜
        ResultEntity comedyTopMovies = recommendService.getTopMoviesOfCategory("喜剧");

        //获取侧边栏显示的爱情片排行榜
        ResultEntity loveTopMovies = recommendService.getTopMoviesOfCategory("爱情");

        //获取侧边栏显示的科幻片排行榜
        ResultEntity scienceTopMovies = recommendService.getTopMoviesOfCategory("科幻");

        //获取侧边栏显示的恐怖片排行榜
        ResultEntity terrorTopMovies = recommendService.getTopMoviesOfCategory("恐怖");

        //获取侧边栏显示的剧情片排行榜
        ResultEntity plotTopMovies = recommendService.getTopMoviesOfCategory("剧情");

        //获取侧边栏显示的战争片排行榜
        ResultEntity warTopMovies = recommendService.getTopMoviesOfCategory("战争");

        modelMap.addAttribute("topMovies",topMovies);

        //category表示是电影专栏
        modelMap.addAttribute("category","movie");
        //放入主页面展示的数据
        modelMap.addAttribute("multipleMoviesList",multipleMoviesList);

        //放入侧边栏要显示的数据
        modelMap.addAttribute("actionTopMovies",actionTopMovies);
        modelMap.addAttribute("comedyTopMovies",comedyTopMovies);
        modelMap.addAttribute("loveTopMovies",loveTopMovies);
        modelMap.addAttribute("scienceTopMovies",scienceTopMovies);
        modelMap.addAttribute("terrorTopMovies",terrorTopMovies);
        modelMap.addAttribute("plotTopMovies",plotTopMovies);
        modelMap.addAttribute("warTopMovies",warTopMovies);

        return "movie_list";
    }

    /**
    *@title:
    *@description: 动漫专栏
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/18 12:01
    **/
    @RequestMapping(value = {"/comic-list.html","/comic/list"})
    @LoginRequired(mustLogin = false)
    public String comicList(ModelMap modelMap){

        ResultEntity<ComicListVO> multipleComicList = cacheService.getMultipleComicList();

        modelMap.addAttribute("category","comic");
        modelMap.addAttribute("multipleComicList",multipleComicList);

        return "comic_list";
    }





    @RequestMapping("/index/{is_comic}")
//    @ResponseBody
    public String getMovieInfoForIndexPage(@PathVariable("is_comic") int is_comic, ModelMap modelMap){

        ResultEntity<List<MovieInfo>> resultEntity = databaseService.getMovieInfoForIndexPage(is_comic);
//        modelAndView.addObject("movieInfos",resultEntity);
        modelMap.addAttribute("movieLeft",resultEntity.getData().get(0));
        modelMap.addAttribute("movieInfos",resultEntity.getData().subList(1,11));



        return "index";
    }




}
