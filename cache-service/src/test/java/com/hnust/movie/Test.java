package com.hnust.movie;

import com.hnust.movie.util.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/17 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RedisUtil redisUtil;

    @org.junit.Test
    public void testJedis(){
        System.out.println(redisUtil.getJedis());
    }

    /**
    *@title:
    *@description: 将数据库的数据导入redis
    *@author:ggh
    *@updateTime: 2020/5/17 21:02
    **/
    public void importToCache() throws Exception {

        Jedis jedis = redisUtil.getJedis();
        String key = "indexInfo";

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie", "root", "root");

        String sql = "select * from m_movie_info";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){

            long mid = resultSet.getLong("mid");
            String movie_name = resultSet.getString("movie_name");
            resultSet.getString("img_urls");
            resultSet.getString("directors");
            resultSet.getString("screen_writer");
            resultSet.getString("main_actors");
            resultSet.getString("categories");
            resultSet.getString("location");
            resultSet.getString("language");
            resultSet.getString("release_date");
            resultSet.getString("run_time");
            resultSet.getString("alias");
            resultSet.getString("summery");
            resultSet.getString("rating_num");
            resultSet.getString("off_shelf");
            resultSet.getString("hot_degree");
            resultSet.getString("view_num");
            resultSet.getString("play_url");


        }


    }

}
