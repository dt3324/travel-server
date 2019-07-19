package com.dengtao.travel.mapper;

import com.dengtao.travel.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (nickName,gender,city,province,avatarUrl,openId) VALUES(#{nickName},#{gender},#{city},#{province},#{avatarUrl},#{openId})")
    void save(User user);
    @Select("select * from user where openId = #{openId}")
    User findByOpenId(String openId);
    @Update("update user set nickName=#{nickName},gender=#{gender},city=#{city},province=#{province},avatarUrl=#{avatarUrl} where openId=#{openId}")
    void update(User user);
    @Select("select * from user where id = #{userId}")
    User findById(Long userId);
}
