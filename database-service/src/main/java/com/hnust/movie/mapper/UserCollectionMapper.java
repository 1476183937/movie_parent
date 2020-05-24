package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.UserCollection;
import com.hnust.movie.entity.po.UserCollectionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectionMapper {
    int countByExample(UserCollectionExample example);

    int deleteByExample(UserCollectionExample example);

    int deleteByPrimaryKey(String collectionId);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    List<UserCollection> selectByExample(UserCollectionExample example);

    UserCollection selectByPrimaryKey(String collectionId);

    int updateByExampleSelective(@Param("record") UserCollection record, @Param("example") UserCollectionExample example);

    int updateByExample(@Param("record") UserCollection record, @Param("example") UserCollectionExample example);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);
}