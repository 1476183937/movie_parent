package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.TopComicsDao;
import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import com.hnust.movie.entity.recommender.TopComics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作动漫top10的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class TopComicsDaoImpl implements TopComicsDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(TopComics topComics) {
        mongoTemplate.save(topComics);
    }

    @Override
    public void update(TopComics topComics) {

        Query query = new Query(Criteria.where("id").is(topComics.getId()));

        Update update = new Update();

        update.set("date",topComics.getDate());
        update.set("movieList",topComics.getMovieList());

        mongoTemplate.updateFirst(query, update,SimilarMovieRecommendation.class);

    }

    @Override
    public List<TopComics> findAll() {

        List<TopComics> all = mongoTemplate.findAll(TopComics.class);

        return all;
    }

    /**
     *@title:
     *@description: 获取最新的top10的动漫数据
     *@param: mid
     *@author:ggh
     *@updateTime: 2020/5/23 10:48
     **/
    @Override
    public TopComics findLatest() {

        List<TopComics> topComics = mongoTemplate.findAll(TopComics.class);

        List<TopComics> topComicsList = topComics.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (topComicsList.size() > 0){
            return topComicsList.get(0);
        }else{
            return null;
        }

    }



    @Override
    public void delete(String id) {

        TopComics templateById = mongoTemplate.findById(id, TopComics.class);

        mongoTemplate.remove(templateById);

    }
}
