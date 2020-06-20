package com.hnust.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.annotation.LoginRequired;
import com.hnust.movie.common.WebUtil;
import com.hnust.movie.config.IdGeneratorSnowflake;
import com.hnust.movie.entity.po.ScanHistory;
import com.hnust.movie.entity.po.UserCollection;
import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.UserCollectionVO;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.PassportService;
import com.hnust.movie.util.CommonUtil;
import com.hnust.movie.util.CookieUtil;
import com.hnust.movie.util.DateUtil;
import com.hnust.movie.util.JwtUtil;
import com.sun.org.apache.bcel.internal.generic.FADD;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/26 18:41
 */
@Controller
public class UserContrller {


    @Autowired
    private PassportService passportService;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
    *@title:
    *@description: 用户给评论点赞
    *@param: commentId
    *@author:ggh
    *@updateTime: 2020/5/31 11:25
    **/
    @PostMapping("/comment/support")
    @ResponseBody
    @LoginRequired(mustLogin = true)
    public ResultEntity commentSupport(String commentId,HttpServletRequest request,HttpServletResponse response){

        //获取用户id
        String userId = WebUtil.getUserId(request, passportService);

        if (StringUtils.isNotBlank(userId)){

        }

        return null;
    }



    @RequestMapping("/login.html")
    public String loginHtml(@RequestParam(value = "returnUrl",required = false,defaultValue = "/") String returnUrl,ModelMap modelMap){

        modelMap.addAttribute("returnUrl",returnUrl);

        return "login02";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity login(String user_name,
                        String user_pwd,
                        @RequestParam(value = "returnUrl",required = false,defaultValue = "index") String returnUrl ,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ModelMap modelMap){

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user_name);
        userInfo.setPassword(user_pwd);

        ResultEntity<String> resultEntity = passportService.login(userInfo);

        //存入cookie
        if ("SUCCESS".equals(resultEntity.getResult())){

            // bin/kafka-console-consumer.sh --bootstrap-server 39.105.36.4:9092 --topic rating22

            //获取ip
            String ip = request.getHeader("x-forwarded-for");//获取通过ngnix转发的客户端ip
            if (StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr(); //获取request里的ip

                //如果request里也没有ip，就直接指定
                if (StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }

            Map<String, Object> userMap = JwtUtil.decode(resultEntity.getData(), "movie", ip);

            //往request域里存放信息
            if (userMap != null){

//                modelMap.addAttribute("userId",userMap.get("userId"));
//                modelMap.addAttribute("nickName",userMap.get("nickName"));
//                modelMap.addAttribute("face",userMap.get("face"));
                request.setAttribute("userId",userMap.get("userId"));
                request.setAttribute("nickName",userMap.get("nickName"));
                request.setAttribute("face",userMap.get("face"));
            }
            //往cookie放token
            Cookie token = null;
            try {
                token = new Cookie("userToken", URLEncoder.encode(resultEntity.getData(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            token.setMaxAge(60*60*24);
            response.addCookie(token);
//            CookieUtil.setCookie(request,response,"userToken",resultEntity.getData(),60*60*24,true);

        }else {
            return resultEntity;
        }

        return ResultEntity.successNoData();
    }


    /**
    *@title:
    *@description: 退出登录状态
    *@param: request
    *@param: response
    *@author:ggh
    *@updateTime: 2020/5/28 10:46
    **/
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){

        //清除cookie中的token
        Cookie userToken = new Cookie("userToken", null);
        userToken.setMaxAge(0);
        response.addCookie(userToken);

        return "index";
    }

    @RequestMapping("/user/info/{userId}")
    @LoginRequired(mustLogin = true)
    public String userInfo(@PathVariable(value = "userId") Long userId, HttpServletRequest request,ModelMap modelMap){

        Cookie[] cookies = request.getCookies();

        String userToken = "";

        for (Cookie cookie : cookies) {
            if ("userToken".equals(cookie.getName())){
                userToken = cookie.getValue();
                break;
            }
        }

        if (StringUtils.isNotBlank(userToken)){

            String ip = CommonUtil.getIpByRequest(request);

            String verify = passportService.verify(userToken, ip);
            Map map = JSONObject.parseObject(verify, Map.class);

            //如果认证成功了，就获取相应的信息
            if ("success".equals(map.get("status"))){
                //获取用户id
                String uid = (String) map.get("userId");
                String face = (String) map.get("face");
                String nickName = (String) map.get("nickName");

                request.setAttribute("userId",uid);
                request.setAttribute("face",face);
                request.setAttribute("nickName",nickName);

                //表示选择了观看收藏选项栏
                modelMap.addAttribute("select","info");

                return "user_info";

            }


        }

        //如果没有取到userToken或认证失败了，都去重新登录
        return "redircet:/login.html?returnUrl="+request.getRequestURL();

    }

    @RequestMapping("/user/info/{userId}/history")
    @LoginRequired(mustLogin = true)
    public String playHistory(@PathVariable(value = "userId") Long userId,ModelMap modelMap){


        ResultEntity<List<ScanHistory>> historyEntity = databaseService.getHistory(userId);

        List<ScanHistory> todayHistoryList = new ArrayList<>();    //存放今天的观看记录
        List<ScanHistory> yesterdayHistoryList = new ArrayList<>();//存放昨天的观看记录
        List<ScanHistory> earlierHistoryList = new ArrayList<>();  //存放昨天之前的观看记录

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String timeStr = simpleDateFormat.format(date);

        int nowTime = Integer.parseInt(timeStr);

        if ("SUCCESS".equals(historyEntity.getResult())){
            for (ScanHistory scanHistory : historyEntity.getData()) {
                //20200512 20200511
                String time = scanHistory.getDate().substring(0, 10).replace("-", "");
                int dataTime = Integer.parseInt(time);

                if (nowTime - dataTime == 0){
                    todayHistoryList.add(scanHistory);
                }else if (nowTime-dataTime==1){
                    yesterdayHistoryList.add(scanHistory);
                }else {
                    earlierHistoryList.add(scanHistory);
                }

            }

            ResultEntity<List<ScanHistory>> todayHistory = ResultEntity.successWithData(todayHistoryList);
            ResultEntity<List<ScanHistory>> yesterdayHistory = ResultEntity.successWithData(yesterdayHistoryList);
            ResultEntity<List<ScanHistory>> earlierHistory = ResultEntity.successWithData(earlierHistoryList);

            modelMap.addAttribute("todayHistory",todayHistory);
            modelMap.addAttribute("yesterdayHistory",yesterdayHistory);
            modelMap.addAttribute("earlierHistory",earlierHistory);

            //表示选择了观看历史选项栏
            modelMap.addAttribute("select","history");

        }

        return "play_history";
    }

    /**
    *@title:
    *@description: 获取用户的收藏记录
    *@param: userId:用户id
    *@param: condition:筛选条件->0表示全部，1表示电影（不含动漫），2表示动漫
    *@author:ggh
    *@updateTime: 2020/5/28 21:38
    **/
    @RequestMapping("/user/info/{userId}/collection")
    @LoginRequired(mustLogin = true)
    public String collection(@PathVariable(value = "userId") Long userId,
                             @RequestParam(value = "condition",required = false,defaultValue = "0") int condition,
                             ModelMap modelMap){

        ResultEntity<List<UserCollectionVO>> collections = databaseService.getCollections(userId);

        List<UserCollectionVO> userCollectionList = collections.getData();

        ResultEntity<List<UserCollectionVO>> collectionsEntity = null;

        //过滤出只是电影而不是动漫的数据
        List<UserCollectionVO> movieList = userCollectionList.stream().filter(x -> !x.getCategories().contains("动画")).collect(Collectors.toList());

        //过滤出只是动漫的数据
        List<UserCollectionVO> comicList = userCollectionList.stream().filter(x -> x.getCategories().contains("动画")).collect(Collectors.toList());

        modelMap.addAttribute("allCollections",collections);
        modelMap.addAttribute("movieCollections",ResultEntity.successWithData(movieList));
        modelMap.addAttribute("comicCollections",ResultEntity.successWithData(comicList));


        /*if (condition == 0){ //查看全部收藏记录
            collectionsEntity = ResultEntity.successWithData(userCollectionList);
            modelMap.addAttribute("collections",collectionsEntity);

        }else if (condition == 1){ //只查看电影收藏记录

            List<UserCollectionVO> movieList = userCollectionList.stream().filter(x -> !x.getCategories().contains("动画")).collect(Collectors.toList());
            modelMap.addAttribute("collections",ResultEntity.successWithData(movieList));
            modelMap.addAttribute("count",movieList.size());

        }else { //只查看动漫收藏记录

            List<UserCollectionVO> comicList = userCollectionList.stream().filter(x -> x.getCategories().contains("动画")).collect(Collectors.toList());
            modelMap.addAttribute("collections",ResultEntity.successWithData(comicList));

        }*/

        //表示选择了观看收藏选项栏
        modelMap.addAttribute("select","collection");

        return "collection";
    }

}
