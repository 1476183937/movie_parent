package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:类别统计的实例
 * @Author: ggh
 * @Date: 2020/5/23 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "topMoviesOfCategory")
public class TopMoviesOfCategory implements Serializable {

    @Id
    private String id;                         //统计id
    private String category;                //统计的类别
    private String date;                    //统计的日期
    private List<MovieInfoMongodb> movieList; //该类别的top10电影列表

}
