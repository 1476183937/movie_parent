package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.SimilarMovieRecommendationDao;
import com.hnust.movie.entity.recommender.SimilarMovieRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作相似度电影推荐的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class SimilarMovieRecommendationDaoImpl implements SimilarMovieRecommendationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(SimilarMovieRecommendation similarMovieRecommendation) {
        mongoTemplate.save(similarMovieRecommendation);
    }

    @Override
    public void update(SimilarMovieRecommendation similarMovieRecommendation) {

        Query query = new Query(Criteria.where("id").is(similarMovieRecommendation.getId()));

        Update update = new Update();

        update.set("date",similarMovieRecommendation.getDate());
        update.set("movieList",similarMovieRecommendation.getSimliarMovies());

        mongoTemplate.updateFirst(query, update,SimilarMovieRecommendation.class);

    }

    @Override
    public List<SimilarMovieRecommendation> findAll() {

        List<SimilarMovieRecommendation> all = mongoTemplate.findAll(SimilarMovieRecommendation.class);

        return all;
    }

    /**
    *@title:
    *@description: 根据mid找出与该电影最新的相似度电影数据
    *@param: mid
    *@author:ggh
    *@updateTime: 2020/5/23 10:48
    **/
    @Override
    public SimilarMovieRecommendation findLatestByMid(Long mid) {

        Query query = new Query(Criteria.where("mid").is(mid));

        //获取所有指定mid的结果
        List<SimilarMovieRecommendation> recommendations = mongoTemplate.find(query, SimilarMovieRecommendation.class);

        //按日期排序，只留下一个
        List<SimilarMovieRecommendation> recommendationList = recommendations.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

//        System.out.println(recommendations);

        if (recommendationList.size() > 0){

            return recommendationList.get(0);
        }else {
            return null;
        }

    }

    @Override
    public void delete(String id) {

        SimilarMovieRecommendation templateById = mongoTemplate.findById(id, SimilarMovieRecommendation.class);

        mongoTemplate.remove(templateById);

    }
}
