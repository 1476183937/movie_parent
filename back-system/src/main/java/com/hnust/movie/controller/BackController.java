package com.hnust.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/7/14 10:00
 */
@Controller
public class BackController {


    @RequestMapping({"/index","/"})
    public String index(){

        return "index";
    }

    @RequestMapping("/admin/web-user")
    public String adminUser(){

        return "web-user";
    }

    @RequestMapping("/admin/database")
    public String database(){

        return "database";
    }

    @RequestMapping("/admin/leaving/message")
    public String leavingMessage(){

        return "leaving_message";
    }

    @RequestMapping("/admin/page/jump/rate")
    public String pageJumpRate(){

        return "page_jump_rate";
    }

    @RequestMapping("/admin/ranking")
    public String ranking(){

        return "ranking";
    }

    @RequestMapping("/admin/real/hot/movie")
    public String realHotMovie(){

        return "real_hot_movie";
    }


    @RequestMapping("/admin/session/statistics")
    public String sessionStatistics(){

        return "session-statistics";
    }

    @RequestMapping("/login")
    public String signIn(){

        return "sign-in";
    }

    @RequestMapping("/admin/top/category")
    public String topCategory(){

        return "top_category";
    }

    @RequestMapping("/admin/top/movies/area")
    public String topMoviesArea(){

        return "top_movies_area";
    }

    @RequestMapping("/admin/top/movies/category")
    public String topMoviesCategory(){

        return "top_movies_category";
    }

    @RequestMapping("/admin/user-visit")
    public String userVisit(){


        return "user-visit.html";
    }


}
