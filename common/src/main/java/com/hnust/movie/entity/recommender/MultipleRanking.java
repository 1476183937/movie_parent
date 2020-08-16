package com.hnust.movie.entity.recommender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * @Title:复合排行榜(包括好评榜、热播榜、北美榜、大陆榜)中的每一条记录的实例
 * @Author: ggh
 * @Date: 2020/8/13 10:19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "multipleRanking")
public class MultipleRanking {

    //统计日期
    private String date;
    //类别
    private String category;
    //电影列表
    private ArrayList<MovieInfoMongodb> movieList;

}
