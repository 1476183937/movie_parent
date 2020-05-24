package com.hnust.movie.service;

import com.hnust.movie.entity.po.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:47
 */

public interface CategoryService {

    List<Category> getAll();

}
