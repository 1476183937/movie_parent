package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 用户的电影推荐实体类
 * @Author: ggh
 * @Date: 2020/5/23 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userRecommendation")
public class UserRecommendation implements Serializable {

    @Id
    private String id;
    private Long uid;                          //用户id
    private String date;                       //统计时间
    private List<MovieInMongodb> movieList;    //推荐电影列表

}
