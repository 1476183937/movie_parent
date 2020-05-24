package com.hnust.movie.service;

import com.hnust.movie.entity.po.Rating;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:51
 */
public interface RatingService {

    Rating isScored(Long uid, Long mid);

    int addRating(Rating rating);

}
