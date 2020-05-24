package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 每个月的top10电影列表
 * @Author: ggh
 * @Date: 2020/5/23 10:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "topMovieOfMonth")
public class TopMovieOfMonth implements Serializable {

    @Id
    private String id;                      //统计id
    private String date;                    //统计日期
    private List<MovieInMongodb> movieList; //每月的top10电影列表

}
