package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 每个电影的相似度电影推荐实体
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "similarMovieRecommendation")
public class SimilarMovieRecommendation implements Serializable {

    @Id
    private String id;                      //统计id
    private Long mid;                       //电影id
    private String date;                    //统计日期
    private List<MovieInfoMongodb> simliarMovies; //该电影的top30相似电影

}
