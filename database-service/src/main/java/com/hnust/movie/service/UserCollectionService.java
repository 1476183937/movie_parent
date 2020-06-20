package com.hnust.movie.service;

import com.hnust.movie.entity.po.UserCollection;
import com.hnust.movie.entity.vo.UserCollectionVO;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:53
 */
public interface UserCollectionService {

    List<UserCollectionVO> getCollection(Long uid);

    int addCollection(UserCollection userCollection);

    UserCollection getByUidAndMid(Long uid, Long mid);

    int deleteCollection(String collectionId);

    int updateCollection(UserCollection userCollection);

}
