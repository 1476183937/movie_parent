package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.UserCollection;
import com.hnust.movie.entity.vo.UserCollectionVO;
import com.hnust.movie.mapper.UserCollectionMapper;
import com.hnust.movie.mapper.UserInfoMapper;
import com.hnust.movie.service.UserCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:53
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Resource
    private UserCollectionMapper userCollectionMapper;

    @Override
    public List<UserCollectionVO> getCollection(Long uid) {

        List<UserCollectionVO> userCollections = null;

        userCollections = userCollectionMapper.queryByUid(uid);

        return userCollections;
    }

    @Override
    public int addCollection(UserCollection userCollection) {

        int result = 0;

        result = userCollectionMapper.insertCollection(userCollection);

        return result;
    }

    @Override
    public UserCollection getByUidAndMid(Long uid, Long mid) {

        UserCollection userCollection = null;
        userCollection = userCollectionMapper.queryByUidAndMid(uid,mid);

        return userCollection;
    }

    @Override
    public int deleteCollection(String collectionId) {

        int result = 0;
        result = userCollectionMapper.deleteCollection(collectionId);

        return result;
    }

    @Override
    public int updateCollection(UserCollection userCollection) {

        int result = 0;
        result = userCollectionMapper.updateCollection(userCollection);
        return result;
    }
}
