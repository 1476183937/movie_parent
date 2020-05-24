package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.TopMoviesDao;
import com.hnust.movie.entity.recommender.TopMovies;
import com.hnust.movie.entity.recommender.TopMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作电影top10的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class TopMoviesDaoImpl implements TopMoviesDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(TopMovies topMovies) {
        mongoTemplate.save(topMovies);
    }

    @Override
    public void update(TopMovies topMovies) {

        Query query = new Query(Criteria.where("id").is(topMovies.getId()));

        Update update = new Update();

        update.set("date",topMovies.getDate());
        update.set("movieList",topMovies.getMovieList());

        mongoTemplate.updateFirst(query, update, TopMovies.class);

    }

    @Override
    public List<TopMovies> findAll() {

        List<TopMovies> all = mongoTemplate.findAll(TopMovies.class);

        return all;
    }

    /**
     *@title:
     *@description: 查询出最新的top10电影(包含动漫)
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/23 10:48
     **/
    @Override
    public TopMovies findLatest() {

        List<TopMovies> topMovies = mongoTemplate.findAll(TopMovies.class);

        //查询出所有，按日期倒序排序，只留一个
        List<TopMovies> topMoviesList = topMovies.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (topMoviesList.size() > 0){
            return topMoviesList.get(0);

        }else {
            return null;
        }
        
    }

    @Override
    public void delete(String id) {

        TopMovies templateById = mongoTemplate.findById(id, TopMovies.class);

        mongoTemplate.remove(templateById);

    }
}
