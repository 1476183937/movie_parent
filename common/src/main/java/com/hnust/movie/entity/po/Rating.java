package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable {

    private Long ratingId; //评分id
    private Long uid;      //用户id
    private Long mid;      //电影id
    private Float rating;  //评分值
    private String date;   //评分日期


}