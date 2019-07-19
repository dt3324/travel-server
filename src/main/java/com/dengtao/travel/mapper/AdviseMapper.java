package com.dengtao.travel.mapper;

import com.dengtao.travel.bean.Advise;
import com.dengtao.travel.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdviseMapper {
    @Insert("INSERT INTO advise (advise,user_id,advise_time) VALUES(#{advise},#{user.userId},#{adviseTime})")
    void save( Advise advise);

    List<Advise> select();
}
