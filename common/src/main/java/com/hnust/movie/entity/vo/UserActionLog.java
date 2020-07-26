package com.hnust.movie.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/7/26 11:32
 */
@AllArgsConstructor
@Data
public class UserActionLog {

    private long time;
    private String userId;
    private String sessionId;
    private String pageId;
    private String searchKeyWord;
    private String clickCategory;
    private String collectCategory;
    private String ratingCategory;
    private String commentCategory;
    private String playCategory;
    private String cilckMovieId;
    private String collectMovieId;
    private String ratingMovieId;
    private String commentMovieId;
    private String playMovieId;



}
