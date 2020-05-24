package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.TopMovieOfWeekDao;
import com.hnust.movie.entity.recommender.TopMovieOfWeek;
import com.hnust.movie.entity.recommender.TopMovieOfWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作每周的top10电影的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class TopMovieOfWeekDaoImpl implements TopMovieOfWeekDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(TopMovieOfWeek topMovieOfWeek) {
        mongoTemplate.save(topMovieOfWeek);
    }

    @Override
    public void update(TopMovieOfWeek topMovieOfWeek) {

        Query query = new Query(Criteria.where("id").is(topMovieOfWeek.getId()));

        Update update = new Update();

        update.set("date",topMovieOfWeek.getDate());
        update.set("movieList",topMovieOfWeek.getMovieList());

        mongoTemplate.updateFirst(query, update, TopMovieOfWeek.class);

    }

    @Override
    public List<TopMovieOfWeek> findAll() {

        List<TopMovieOfWeek> all = mongoTemplate.findAll(TopMovieOfWeek.class);

        return all;
    }

    /**
     *@title:
     *@description: 查询出最新的每周电影(包含动漫)排行榜
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/23 10:48
     **/
    @Override
    public TopMovieOfWeek findLatest() {

        List<TopMovieOfWeek> movieOfWeeks = mongoTemplate.findAll(TopMovieOfWeek.class);

        //查询出所有，按日期倒序排序，只留一个
        List<TopMovieOfWeek> movieOfWeekList = movieOfWeeks.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (movieOfWeekList.size() > 0){
            return movieOfWeekList.get(0);

        }else {
            return null;
        }
        
    }

    @Override
    public void delete(String id) {

        TopMovieOfWeek templateById = mongoTemplate.findById(id, TopMovieOfWeek.class);

        mongoTemplate.remove(templateById);

    }
}
