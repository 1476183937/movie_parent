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

import java.util.ArrayList;
import java.util.HashSet;
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
    public List<TopMoviesOfCategory> findLatestByCategories(String categories) {

        String[] categoriesSplit = categories.split("-");

        Criteria[] criterias = new Criteria[categoriesSplit.length];
        Criteria criteria = new Criteria();

        for (int i = 0; i < categoriesSplit.length; i++) {
            criterias[i]=Criteria.where("category").is(categoriesSplit[i]);
        }

        criteria.orOperator(criterias);

        /*for (String category : categoriesSplit) {
            criterias[0]=Criteria.where("category").is(category);
            criteria.orOperator(criterias);
//            criteria.orOperator(Criteria.where("category").is(category));
        }*/
//        criteria.orOperator(Criteria.where("category").is("恐怖"),Criteria.where("category").is("奇幻"),Criteria.where("category").is("历史"));

        Query query = new Query(criteria);

        List<TopMoviesOfCategory> categoryList = mongoTemplate.find(query, TopMoviesOfCategory.class);

//        List<TopMoviesOfCategory> moviesOfCategoryList = categoryList.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).limit(1).collect(Collectors.toList());
        List<TopMoviesOfCategory> moviesOfCategoryList = categoryList.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).collect(Collectors.toList());
//        categoryList.stream().sorted((a,b)->a.getMovieList())

        List<TopMoviesOfCategory> resultList = new ArrayList<>();

        //记录已经取过的类别
        HashSet<String> existCategory = new HashSet<>();

        for (TopMoviesOfCategory topMoviesOfCategory : moviesOfCategoryList) {
            //是指定需要查询的类别并且还没有取过该类别就添加到结果集里
            if (!existCategory.contains(topMoviesOfCategory.getCategory()) && categories.contains(topMoviesOfCategory.getCategory())){
                resultList.add(topMoviesOfCategory);
            }

        }

        if (resultList.size() > 0){
            return moviesOfCategoryList;

        }else {
            return null;
        }

    }


}
