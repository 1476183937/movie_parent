package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.UserCollection;
import com.hnust.movie.entity.po.UserCollectionExample;
import com.hnust.movie.entity.vo.UserCollectionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserCollectionMapper {


    //根据用户id查询该用户的收藏记录
//    @Select("SELECT mid,image,movie_name,date " +
//            "FROM m_user_collection WHERE uid=#{uid} ORDER BY date DESC")
//    List<UserCollection> queryByUid(@Param("uid") Long uid);

    @Select("SELECT uc.*,categories FROM m_movie_info mmi\n" +
            "JOIN\n" +
            "(SELECT * FROM m_user_collection WHERE uid=#{uid} AND deleted=0) uc " +
            "ON mmi.mid=uc.mid ORDER BY date DESC")
    List<UserCollectionVO> queryByUid(@Param("uid") Long uid);

    //添加用户收藏记录
    @Insert("INSERT INTO `m_user_collection`(`collection_id`, `mid`, `uid`, `image`, " +
            "`movie_name`, `date`, `deleted`) VALUES " +
            "(#{collectionId}, #{mid}, #{uid}, #{image}, #{movieName}, #{date}, 0);")
    int insertCollection(UserCollection userCollection);

    //根据用户id和电影id获取相应的收藏记录
    @Select("SELECT * FROM m_user_collection WHERE uid=#{uid} AND mid=#{mid} AND deleted=0;")
    UserCollection queryByUidAndMid(@Param("uid") Long uid,@Param("mid")Long mid);

    //删除相应的收藏记录
    @Update("UPDATE m_user_collection SET deleted=1 WHERE collection_id=#{collectionId};")
    int deleteCollection(String collectionId);

    //更新记录
    @Update("UPDATE `m_user_collection` SET `mid` = #{mid}, `uid` = #{uid}, " +
            "`image` = #{image}, `movie_name` = #{movieName}, `date` = #{date}, `deleted` = #{deleted} WHERE `collection_id` = #{collectionId};")
    int updateCollection(UserCollection userCollection);




}