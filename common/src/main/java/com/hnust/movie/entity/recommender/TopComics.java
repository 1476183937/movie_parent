package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: top10的动漫列表
 * @Author: ggh
 * @Date: 2020/5/23 10:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "topComics")
public class TopComics implements Serializable {

    @Id
    private String id;                      //统计id
    private String date;                    //统计日期
    private List<MovieInMongodb> movieList; //动漫top10列表

}
