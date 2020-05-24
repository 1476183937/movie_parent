package com.hnust.movie;

import com.hnust.movie.config.IdGeneratorSnowflake;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/14 11:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {


    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @org.junit.Test
    public void idTest(){
        System.out.println(idGeneratorSnowflake.snowflakeId(0, 1));
    }

    public static void main(String[] args) {


        String aa = "";

        try {
            importUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void importUserInfo() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8", "root", "root");

        String sql = "SELECT DISTINCT uid FROM m_comment c JOIN m_movie_info m ON c.mid=m.mid LIMIT 2000;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        String[] string = {"aaa","vvv","ccc","dsf","dfsaf","dsfafa","dddd","eeee","fdsa","rter","uiiuiui","uiouo","yuii",
                "aaa11","vvv22","ccc333","dsf3","dfsaf3","dsfafa4","dddd4","eeee5","fdsa5","rter6","uiiuiui6","uiouo7","yuii8"};

        while (resultSet.next()){
            long uid = resultSet.getLong("uid");

            Random random = new Random();
            String userName = random.nextInt(5000) + "";
            String password = random.nextInt(5000) + "";
            String nickName = random.nextInt(5000) + string[random.nextInt(25)];

            String registry_time = "2016-10-11";

            String birthday = "2017-12-12";
            String phone = "1520037676"+random.nextInt(10);

            String gender = random.nextInt(10) > 5 ?"m":"f";

            String face = "https://img1.doubanio.com/icon/ul196872052-8.jpg";

            String sql1 = "INSERT INTO `m_userinfo`(`uid`, `username`, `password`, `nick_name`, `register_time`, `birthday`, `phone`, `gender`, `face`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(sql1);

            /*ps.setLong(0,uid);
            ps.setString(1,userName);
            ps.setString(2,password);
            ps.setString(3,nickName);
            ps.setString(4,registry_time);
            ps.setString(5,birthday);
            ps.setString(6,phone);
            ps.setString(7,gender);
            ps.setString(8,face);*/

            ps.setLong(1,uid);
            ps.setString(2,userName);
            ps.setString(3,password);
            ps.setString(4,nickName);
            ps.setString(5,registry_time);
            ps.setString(6,birthday);
            ps.setString(7,phone);
            ps.setString(8,gender);
            ps.setString(9,face);

            ps.execute();

        }




    }

}
