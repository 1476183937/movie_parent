package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.Location;
import com.hnust.movie.mapper.LocationMapper;
import com.hnust.movie.service.LocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/20 10:52
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Resource
    private LocationMapper locationMapper;

    /**
    *@title:
    *@description: 获取所有地区信息
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/20 10:57
    **/
    @Override
    public List<Location> getAll() {

        List<Location> locationList = locationMapper.queryAll();

        return locationList;
    }
}
