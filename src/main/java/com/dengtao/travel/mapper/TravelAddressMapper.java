package com.dengtao.travel.mapper;

import com.dengtao.travel.bean.TravelAddress;
import com.dengtao.travel.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelAddressMapper {

    List<TravelAddress> search(@Param("keyword") String keyword);
    List<TravelAddress> searchAll();
}
