package com.hnust.movie.controller;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.recommender.MultipleRankings;
import com.hnust.movie.entity.recommender.UserRecommendation;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.RecommendService;
import com.hnust.movie.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Title:专门用户测试的controller
 * @Author: ggh
 * @Date: 2020/5/17 12:10
 */
@Controller
public class TestController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private SearchService searchService;


    @RequestMapping("/dbTest")
    @ResponseBody
    public ResultEntity<MovieInfoInCache> databaseTest(){

        ResultEntity<MovieInfoInCache> detailInfo = databaseService.getDetailInfo(1292348L);
        return detailInfo;

    }

    @RequestMapping("/cacheTest")
    @ResponseBody
    public ResultEntity<MovieInfoInCache> cacheTest(){

        ResultEntity<MovieInfoInCache> detailInfo = cacheService.getMovieInfoFromCache(1292348L);
        return detailInfo;

    }

    @RequestMapping("/top/test")
    @ResponseBody
    public ResultEntity<List<MovieInfoInCache>> getTopRatingMovies(){

        return databaseService.getTopRatingMovies(20,20);
    }

    @RequestMapping("/recommendTest")
    @ResponseBody
    public ResultEntity recommendTest(){

        ResultEntity topMovies = recommendService.getTopMovies();

        return topMovies;

    }

    @RequestMapping("/searchTest")
    @ResponseBody
    public ResultEntity searchTest(){

        ResultEntity<SearchResultVO> infoByKw = searchService.getMovieInfoByKw("火", 1, 10);
        return infoByKw;

    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "test test";
    }

//    @Resource
//    private KafkaTemplate<String , Object> kafkaTemplate;
//
//    @Value("${kafka.MovieRating.topic}")
//    private String topic;

//    @RequestMapping("/kafka/test")
//    @ResponseBody
//    public String kafkaTest(){
//
//        System.out.println(topic);
//        kafkaTemplate.send(topic,"12|22|33|44");
//
//        return "success";
//    }

    @RequestMapping("/test1")
    public String test1(@RequestParam(value = "val",required = false) String val, ModelMap modelMap){
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
        modelMap.addAttribute("testVal",UUID.randomUUID().toString()+"val");

        System.out.println("val===" + val);

        return "test1";
    }


    @RequestMapping("/test2")
    public String test2(ModelMap modelMap){
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());

        return "test2";
    }

    @RequestMapping("/change1")
    @ResponseBody
    public void change1(ModelMap modelMap){
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
    }

    @RequestMapping("/change2")
    @ResponseBody
    public void change2(ModelMap modelMap){
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());
    }

    @RequestMapping("/test3")
    public String test3(ModelMap modelMap){
//        modelMap.addAttribute("test2Text","222222");
//        this.test1(modelMap);
//        this.test2(modelMap);
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());
        modelMap.addAttribute("testVal",UUID.randomUUID().toString()+"val");

        return "test3";
    }

    @RequestMapping("/test4")
    public String test4(){

        return "test4";
    }

    @RequestMapping("/user/recommend/test")
    @ResponseBody
    public ResultEntity<UserRecommendation> userTest(){

        return recommendService.getUserRecommendationDao(570140L);

    }

    @RequestMapping("/multiple/test")
    @ResponseBody
    public ResultEntity<MultipleRankings> multipleTest(){

        return recommendService.getLatestMultipleRankings();
    }

}
