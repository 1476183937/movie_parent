package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.MultipleRankingDao;
import com.hnust.movie.entity.recommender.MultipleRanking;
import com.hnust.movie.entity.recommender.MultipleRankings;
import com.hnust.movie.entity.recommender.TopComics;
import com.hnust.movie.entity.recommender.TopMovieOfMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/8/13 10:53
 */
@Component
public class MultipleRankingDaoImpl implements MultipleRankingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(MultipleRanking multipleRanking) {
        mongoTemplate.save(multipleRanking);
    }

    @Override
    public void update(MultipleRanking multipleRanking) {
        Query query = new Query(Criteria.where("date").is(multipleRanking.getDate()));

        Update update = new Update();

        update.set("date",multipleRanking.getDate());
        update.set("category",multipleRanking.getCategory());
        update.set("movieList",multipleRanking.getMovieList());

        mongoTemplate.updateFirst(query, update, MultipleRanking.class);
    }

    @Override
    public List<MultipleRanking> findAll() {
        List<MultipleRanking> multipleRankings = mongoTemplate.findAll(MultipleRanking.class);
        return multipleRankings;
    }

    @Override
    public void delete(String date, String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("date").is(date));
        query.addCriteria(Criteria.where("category").is(category));
        mongoTemplate.remove(query,MultipleRanking.class);
    }

    /**
    *@title:
    *@description: 获取最新的复合排行榜数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/8/13 11:09
    **/
    @Override
    public MultipleRankings findLatest() {

        List<MultipleRanking> allMultipleRanking = mongoTemplate.findAll(MultipleRanking.class);

        List<MultipleRanking> multipleRankingssList = allMultipleRanking.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(4).collect(Collectors.toList());

        MultipleRankings multipleRankings = new MultipleRankings();
        multipleRankings.setMultipleRankings(multipleRankingssList);

        return multipleRankings;

    }
}
