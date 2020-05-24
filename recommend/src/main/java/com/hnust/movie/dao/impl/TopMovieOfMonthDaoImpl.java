package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.TopMovieOfMonthDao;
import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.recommender.TopMovieOfMonth;
import com.hnust.movie.entity.recommender.TopMovieOfMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作每个月top10的电影(包含动漫)的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class TopMovieOfMonthDaoImpl implements TopMovieOfMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(TopMovieOfMonth topMovieOfMonth) {
        mongoTemplate.save(topMovieOfMonth);
    }

    @Override
    public void update(TopMovieOfMonth topMovieOfMonth) {

        Query query = new Query(Criteria.where("id").is(topMovieOfMonth.getId()));

        Update update = new Update();

        update.set("date",topMovieOfMonth.getDate());
        update.set("movieList",topMovieOfMonth.getMovieList());

        mongoTemplate.updateFirst(query, update, TopMovieOfMonth.class);

    }

    @Override
    public List<TopMovieOfMonth> findAll() {

        List<TopMovieOfMonth> all = mongoTemplate.findAll(TopMovieOfMonth.class);

        return all;
    }

    /**
     *@title:
     *@description: 获取最新的每个月的top电影(包含动漫)数据
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/23 10:48
     **/
    @Override
    public TopMovieOfMonth findLatest() {

        List<TopMovieOfMonth> movieOfMonths = mongoTemplate.findAll(TopMovieOfMonth.class);

        //查询出所有，按日期倒序排序，只留一个
        List<TopMovieOfMonth> movieOfMonthList = movieOfMonths.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (movieOfMonthList.size() > 0){
            return movieOfMonthList.get(0);

        }else {
            return null;
        }

    }

    @Override
    public void delete(String id) {

        TopMovieOfMonth templateById = mongoTemplate.findById(id, TopMovieOfMonth.class);

        mongoTemplate.remove(templateById);

    }
}
