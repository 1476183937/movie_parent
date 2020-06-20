package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 9:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInMongodb implements Serializable {

    private Long mid;           //电影id
    private String imgUrls;     //图片
//    private String imgUrl;     //图片
    private String movieName;   //电影名称
//    private String movie_name;   //电影名称
//    private Float ratingNum;    //评分
    private String ratingNum;    //评分
    private String score;        //相似度：在推荐时有用
    private String hotDegree;        //热度值

}
