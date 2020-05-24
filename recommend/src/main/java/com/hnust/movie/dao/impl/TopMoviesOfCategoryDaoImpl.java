package com.hnust.movie.dao.impl;

import com.hnust.movie.dao.TopMoviesOfCategoryDao;
import com.hnust.movie.entity.recommender.TopMovies;
import com.hnust.movie.entity.recommender.TopMoviesOfCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 操作每个类别的top10的电影的表
 * @Author: ggh
 * @Date: 2020/5/23 10:01
 */
@Component
public class TopMoviesOfCategoryDaoImpl implements TopMoviesOfCategoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(TopMoviesOfCategory topMoviesOfCategory) {
        mongoTemplate.save(topMoviesOfCategory);
    }

    @Override
    public void update(TopMoviesOfCategory topMoviesOfCategory) {

        Query query = new Query(Criteria.where("id").is(topMoviesOfCategory.getId()));

        Update update = new Update();

        update.set("date",topMoviesOfCategory.getDate());
        update.set("movieList",topMoviesOfCategory.getMovieList());

        mongoTemplate.updateFirst(query, update, TopMoviesOfCategory.class);

    }

    @Override
    public List<TopMoviesOfCategory> findAll() {

        List<TopMoviesOfCategory> all = mongoTemplate.findAll(TopMoviesOfCategory.class);

        return all;
    }

    

    @Override
    public void delete(String id) {

        TopMoviesOfCategory templateById = mongoTemplate.findById(id, TopMoviesOfCategory.class);

        mongoTemplate.remove(templateById);

    }

    /**
    *@title:
    *@description: 获取指定类别的最新的top电影(包含动漫)数据
    *@param: category
    *@author:ggh
    *@updateTime: 2020/5/23 12:48
    **/
    @Override
    public TopMoviesOfCategory findLatestByCategory(String category) {

        Query query = new Query(Criteria.where("category").is(category));

        List<TopMoviesOfCategory> categoryList = mongoTemplate.find(query, TopMoviesOfCategory.class);

        List<TopMoviesOfCategory> moviesOfCategoryList = categoryList.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());

        if (moviesOfCategoryList.size() > 0){
            return moviesOfCategoryList.get(0);

        }else {
            return null;
        }

    }
}
