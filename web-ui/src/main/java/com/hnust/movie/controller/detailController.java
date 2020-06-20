package com.hnust.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.annotation.LoginRequired;
import com.hnust.movie.config.IdGeneratorSnowflake;
import com.hnust.movie.entity.po.*;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.CommentVO2;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.PassportService;
import com.hnust.movie.service.RecommendService;
import com.hnust.movie.util.CommonUtil;
import com.hnust.movie.util.CookieUtil;
import com.hnust.movie.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title:电影详情页的controller
 * @Author: ggh
 * @Date: 2020/5/15 17:49
 */
@Controller
public class detailController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private PassportService passportService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Resource
    private KafkaTemplate<String , Object> kafkaTemplate;

    @Value("${kafka.MovieRating.topic}")
    private String topic;


    /**
    *@title:
    *@description: 播放指定电影id的视频
    *@author:ggh
    *@updateTime: 2020/5/16 10:39
    **/
    @RequestMapping("/play/{movieId}.html")
    @LoginRequired(mustLogin = false)
    public String play(@PathVariable("movieId") Long movieId,
                       ModelMap modelMap,
                       HttpServletRequest request){


        //获取周月的电影排行榜
        ResultEntity topMovieOfMonth = recommendService.getTopMovieOfMonth();
        ResultEntity topMovieOfWeek = recommendService.getTopMovieOfWeek();

        modelMap.addAttribute("topMovieOfMonth",topMovieOfMonth);
        modelMap.addAttribute("topMovieOfWeek",topMovieOfWeek);


        int totalPage = 0, count = 0;
        ResultEntity<List<CommentVO>> commentByMid = null;
        ResultEntity<CommentVO> commentVOResultEntity = null;

        ResultEntity<MovieInfo> movieInfoFromCache = cacheService.getMovieInfoFromCache(movieId);

//        String urls = movieInfo.getImgUrls();
//        String imgUrls = movieInfoFromCache.getData().getImgUrls();

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
            commentVO2.setCurrentPage(1);
            commentVO2.setNextPage(2);
            commentVO2.setPrePage(1);
            commentVO2.setTotalPage(totalPage);
            commentVO2.setCommentVOList(commentByMid.getData());

            modelMap.addAttribute("commentResult", ResultEntity.successWithData(commentVO2));

            //获取token
            Cookie[] cookies = request.getCookies();
            String userToken = "";
            if (cookies != null){
                for (Cookie cookie : cookies) {
                    if ("userToken".equals(cookie.getName())){
                        userToken = cookie.getValue();
                        break;
                    }
                }

                //如果用户登录了就记录观看记录，没有登录就不记录了
                if (StringUtils.isNotBlank(userToken)){
                    //添加观看历史记录
                    ScanHistory scanHistory = new ScanHistory();

                    String ip = CommonUtil.getIpByRequest(request);

                    //验证token
                    String verify = passportService.verify(userToken, ip);
                    Map userMap = JSONObject.parseObject(verify, Map.class);

                    //获取用户id
                    String userId = "";
                    if ("success".equals(userMap.get("status"))){
                        if (userMap != null){
                            userId = (String) userMap.get("userId");
                        }
                    }else {
                        return "redirect:/login.html?returnUrl="+request.getRequestURL();
                    }

                    scanHistory.setUid(Long.parseLong(userId));
                    String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                    scanHistory.setDate(date);
                    scanHistory.setDeleted(0);
                    scanHistory.setMid(movieId);
                    scanHistory.setMovieName(movieInfoFromCache.getData().getMovieName());

                    scanHistory.setImage(movieInfoFromCache.getData().getImgUrls());
//
                    long scanId=222L;
                    try {
                        scanId = idGeneratorSnowflake.snowflakeId(2, 2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    scanHistory.setScanId(scanId+"");

                    //添加记录
                    databaseService.addHistory(scanHistory);

                }

            }

            return "moviePlay";
        }

    }

    /**
    *@title:
    *@description: 用户给电影评分
    *@param: mid：电影id
    *@param: ratingNum：评分值
    *@author:ggh
    *@updateTime: 2020/5/24 11:24
    **/
    @RequestMapping("/detail/add/rating")
    @ResponseBody
    @LoginRequired(mustLogin = true)
    public ResultEntity rating(@RequestBody Rating rating, HttpServletRequest request, HttpServletResponse response){


        //获取uid
        String oldToken = CookieUtil.getCookieValue(request, "userToken", true);

        //获取ip
        String ip = CommonUtil.getIpByRequest(request);

        //验证token
        String verify = passportService.verify(oldToken, ip);
        Map userMap = JSONObject.parseObject(verify, Map.class);
        String userId = "";
        if(userMap != null){
            userId = (String) userMap.get("userId");
        }else {
            //解析失败了，就要用户重新登录
            return ResultEntity.failed("REDIRECT");
        }

        //判断该用户是否对该电影评分过
        ResultEntity resultEntity = databaseService.isScored(Long.parseLong(userId), rating.getMid());
        if ("SUCCESS".equals(resultEntity.getResult())){
            return ResultEntity.failed("您已对该电影评分过");
        }else {
            //如果没有评分过写入数据库
            Rating rating1 = new Rating();
            rating1.setUid(Long.parseLong(userId));
            rating1.setMid(rating.getMid());
            rating1.setRating(rating.getRating()*20);
            ResultEntity result = databaseService.addRating(rating1);

            if ("SUCCESS".equals(result.getResult())){

                //发送评分信息到kafka
                String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                String message = userId + "|" + rating.getMid() + "|" + rating.getRating()+"|"+date;
                kafkaTemplate.send(topic,message);

                return ResultEntity.successNoData();
            }else {
                return ResultEntity.failed("评分失败");
            }

        }


    }


    @RequestMapping(value = "/detail/add/collection")
    @ResponseBody
    @LoginRequired(mustLogin = true)
    public ResultEntity addCollection(@RequestParam("mid") Long mid,HttpServletRequest request){

        UserCollection userCollection = new UserCollection();

        ResultEntity<MovieInfo> movieInfoFromCache = cacheService.getMovieInfoFromCache(mid);

        //获取ip
        String ip = CommonUtil.getIpByRequest(request);

        //获取cookie
        Cookie[] cookies = request.getCookies();
        String userToken = "";
        for (Cookie cookie : cookies) {
            if ("userToken".equals(cookie.getName())){
                userToken = cookie.getValue();
                break;
            }

        }

        //验证并获取用户id
        String verify = passportService.verify(userToken, ip);
        Map map = JSONObject.parseObject(verify, Map.class);
        String userId = "";
        if ("success".equals(map.get("status"))){
            userId = (String) map.get("userId");
        }

        //判断用户是否收藏过该电影
        ResultEntity<UserCollection> queryResult = databaseService.getCollectionByUidAndMid(Long.parseLong(userId), mid);

        if ("SUCCESS".equals(queryResult.getResult())) {
            //已收藏过就返回提示已收藏过
            return ResultEntity.failed("COLLECTED");
        }

        //生成唯一的收藏id
        Long collectId = 0L;
        collectId = idGeneratorSnowflake.snowflakeId(1, 1);

        //获取日期
        String date = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        userCollection.setMid(mid);
        userCollection.setMovieName(movieInfoFromCache.getData().getMovieName());
        userCollection.setImage(movieInfoFromCache.getData().getImgUrls());
        userCollection.setUid(Long.parseLong(userId));
        userCollection.setCollectionId(collectId+"");
        userCollection.setDate(date);
        userCollection.setDeleted("0");

        ResultEntity resultEntity = databaseService.addCollection(userCollection);


        if ("SUCCESS".equals(resultEntity.getResult())){
            return ResultEntity.successNoData();
        }
        return ResultEntity.failed("add failed");

    }

}
