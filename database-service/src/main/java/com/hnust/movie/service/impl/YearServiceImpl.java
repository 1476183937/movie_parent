package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.Year;
import com.hnust.movie.mapper.YearMapper;
import com.hnust.movie.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/20 10:52
 */
@Service
public class YearServiceImpl implements YearService {

    @Resource
    private YearMapper yearMapper;

    /**
    *@title:
    *@description: 获取所有年份信息
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/20 10:58
    **/
    @Override
    public List<Year> getAll() {

        List<Year> yearList = yearMapper.queryAll();

        return yearList;
    }
}
