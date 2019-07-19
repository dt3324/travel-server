package com.dengtao.travel.mapper;

import com.dengtao.travel.bean.TravelAddressDetail;
import com.dengtao.travel.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TravelAddressDetailMapper {
    @Insert("INSERT INTO user (nickname,headimg) VALUES(#{nickname},#{headimg})")
    void save(User user);
    @Select("select text,images from traveladdressdetail where traveladdress_id=#{traveladdressId}")
    TravelAddressDetail select(Long traveladdressId );
}
