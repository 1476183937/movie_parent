package com.hnust.movie;

import com.hnust.movie.dao.SimilarMovieRecommendationDao;
import com.hnust.movie.dao.TopMoviesOfCategoryDao;
import com.hnust.movie.dao.UserRecommendationDao;
import com.hnust.movie.entity.recommender.MovieInMongodb;
import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.recommender.TopMoviesOfCategory;
import com.hnust.movie.entity.recommender.UserRecommendation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.ArrayList;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 10:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private SimilarMovieRecommendationDao similarMovieRecommendationDao;

    @Autowired
    private TopMoviesOfCategoryDao topMoviesOfCategoryDao;

    @Autowired
    private UserRecommendationDao userRecommendationDao;

    @org.junit.Test
    public void test(){


        /*SimilarMovieRecommendation similarMovieRecommendation = new SimilarMovieRecommendation();
        similarMovieRecommendation.setId("99");
        similarMovieRecommendation.setDate("2020/05/12");
        similarMovieRecommendation.setMid(10545939l);
        ArrayList<MovieInMongodb> movieInMongodbs = new ArrayList<>();
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(1294963l,"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p907410217.webp","一个都不能少","5.4","0.8961422007181445"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(10467125l,"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1790937541.webp","海贼王剧场版Z ONE PIECE FILM Z","6.4","0.8829420116487671"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(4237879l,"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p500566270.webp","人在囧途","5.4","0.943238530471169"));

        movieInMongodbs.add(new MovieInMongodb(27615564l,"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529136453.webp","的士速递5 Taxi 5","7.4","0.8697266273460135"));
        movieInMongodbs.add(new MovieInMongodb(1294963l,"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p907410217.webp","一个都不能少","5.4","0.8961422007181445"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(10467125l,"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1790937541.webp","海贼王剧场版Z ONE PIECE FILM Z","6.4","0.8829420116487671"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));
        movieInMongodbs.add(new MovieInMongodb(3287562l,"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p792776858.webp","神偷奶爸 Despicable Me","6.3","0.8566788980683018"));
        movieInMongodbs.add(new MovieInMongodb(26266919l,"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2557157554.webp","最好的我们","5.4","0.943238530471169"));

        similarMovieRecommendation.setMovieList(movieInMongodbs);

        similarMovieRecommendationDao.save(similarMovieRecommendation);*/

//        SimilarMovieRecommendation latestByMid = similarMovieRecommendationDao.findLatestByMid(10545939l);
//
//        System.out.println(latestByMid);

//        TopMoviesOfCategory latestByCategory = topMoviesOfCategoryDao.findLatestByCategory("爱情");
//        System.out.println(latestByCategory);

        UserRecommendation latestByUid = userRecommendationDao.findLatestByUid(570140l);

        System.out.println(latestByUid);

//        similarMovieRecommendationDao.findLatestByMid()

    }



}
