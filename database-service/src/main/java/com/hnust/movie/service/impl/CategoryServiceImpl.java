package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.Category;
import com.hnust.movie.mapper.CategoryMapper;
import com.hnust.movie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:49
 */
@Service
public class CategoryServiceImpl implements CategoryService {

//    @Autowired
    @Resource
    private CategoryMapper categoryMapper;

    /**
    *@title:
    *@description: 获取所有类别信息
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/20 10:54
    **/
    @Override
    public List<Category> getAll() {

        List<Category> categoryList = categoryMapper.queryAll();

        return categoryList;
    }
}
