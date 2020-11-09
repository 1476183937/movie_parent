package com.hnust.movie.webUtils;

import com.hnust.movie.entity.recommender.MovieInfoMongodb;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.DatabaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/8/18 12:32
 */
public class WebUtils {

    static Random random = new Random();

    //随机获取一些评分较高的电影
    public static List<MovieInfoMongodb> getTopRankingMovies(DatabaseService databaseService){

        int start = random.nextInt(4000);
        int size=12;

        ResultEntity<List<MovieInfoInCache>> topRatingMovies = databaseService.getTopRatingMovies(start, size);

        //存放该用户的推荐数据的集合
        ArrayList<MovieInfoMongodb> userRescList = new ArrayList<>();
        for (MovieInfoInCache movieInfoInCache : topRatingMovies.getData()) {
            MovieInfoMongodb movieInfoMongodb = new MovieInfoMongodb();
            BeanUtils.copyProperties(movieInfoInCache,movieInfoMongodb);
            userRescList.add(movieInfoMongodb);
        }

        return userRescList;
    }


}
