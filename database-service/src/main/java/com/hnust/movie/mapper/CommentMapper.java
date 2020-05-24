package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Comment;
import com.hnust.movie.entity.po.CommentExample;
import com.hnust.movie.entity.vo.CommentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    //根据电影id获取该电影的评论
    /*@Select("SELECT count(*) count, t1.*, u.username,u.face FROM " +
            "(SELECT comment_id,uid,mid,content,date,like_num,dislike_num FROM m_comment WHERE deleted=0) t1 " +
            "JOIN m_userinfo u ON t1.uid=u.uid WHERE t1.mid=#{mid} limit 0,10")*/
    @Select("SELECT " +
            "t3.*," +
            " u.username," +
            " u.face " +
            "FROM " +
            " ( " +
            " SELECT " +
            "t1.*, " +
            "t2.* " +
            "FROM " +
            "( SELECT comment_id, uid, mid, content, date, like_num, dislike_num FROM m_comment WHERE deleted = 0 AND mid = #{mid} ) t2\n" +
            "JOIN ( SELECT count(*) count FROM m_comment WHERE deleted = 0 AND mid=#{mid}) t1" +
            ") t3 " +
            "JOIN m_userinfo u ON t3.uid = u.uid  ORDER BY date DESC LIMIT #{startPage},10;")
    List<CommentVO> selectCommentByMid(@Param("mid") Long mid,@Param("startPage") int startPage);

    //发布评论
    @Insert("INSERT INTO `m_comment`(`comment_id`, `uid`, `mid`, `content`, `date`, " +
            "`deleted`, `like_num`, `dislike_num`) VALUES (#{commentId}, #{uid}, #{mid}," +
            " #{content}, " +
            "#{date}, 0, 0, 0);")
    int insertComment(Comment comment);


    //=======================以下为逆向工程生成===============================

    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(String commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}