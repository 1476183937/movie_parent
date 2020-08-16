package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.UserRecommendationDao;
import com.hnust.movie.entity.recommender.UserRecommendation;
import com.hnust.movie.entity.recommender.UserRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作用户推荐的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class UserRecommendationDaoImpl implements UserRecommendationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(UserRecommendation userRecommendation) {
        mongoTemplate.save(userRecommendation);
    }

    @Override
    public void update(UserRecommendation userRecommendation) {

        Query query = new Query(Criteria.where("id").is(userRecommendation.getId()));

        Update update = new Update();

        update.set("date",userRecommendation.getDate());
        update.set("movieList",userRecommendation.getUserResc());

        mongoTemplate.updateFirst(query, update, UserRecommendation.class);

    }

    @Override
    public List<UserRecommendation> findAll() {

        List<UserRecommendation> all = mongoTemplate.findAll(UserRecommendation.class);

        return all;
    }



    @Override
    public void delete(String id) {

        UserRecommendation templateById = mongoTemplate.findById(id, UserRecommendation.class);

        mongoTemplate.remove(templateById);

    }

    /**
    *@title:
    *@description: 根据uid获取该用户的推荐电影(包含动漫)列表
    *@param: uid
    *@author:ggh
    *@updateTime: 2020/5/23 12:44
    **/
    @Override
    public UserRecommendation findLatestByUid(Long uid) {

        Query query = new Query(Criteria.where("uid").is(uid));

        List<UserRecommendation> recommendations = mongoTemplate.find(query, UserRecommendation.class);

        List<UserRecommendation> userRecommendationsList = recommendations.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (userRecommendationsList.size() > 0){
            return userRecommendationsList.get(0);
        }else {
            return null;
        }

    }
}
