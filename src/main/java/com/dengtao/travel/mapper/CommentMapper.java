package com.dengtao.travel.mapper;

import com.dengtao.travel.bean.Comment;
import com.dengtao.travel.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment (commenttext,user_id,parent_comment_id,to_user_id,comment_time) VALUES(#{commentText},#{user.id},#{pId},#{toUser.id},#{commentTime})")
    void save(Comment comment);
    List<Comment> select(@Param("pId") Long pId);
}
