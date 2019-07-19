package com.dengtao.travel.service.impl;

import com.dengtao.travel.bean.TravelAddress;
import com.dengtao.travel.bean.User;
import com.dengtao.travel.mapper.TravelAddressMapper;
import com.dengtao.travel.mapper.UserMapper;
import com.dengtao.travel.service.IAdviseService;
import com.dengtao.travel.service.ITravelAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelAddressServiceImpl implements ITravelAddressService {
    @Autowired
    private TravelAddressMapper travelAddressMapper;
    @Override
    public List<TravelAddress> search(String keyword) {
        List<TravelAddress> list = travelAddressMapper.search(keyword);
        return list;
    }

    @Override
    public List<TravelAddress> searchAll() {
        return travelAddressMapper.searchAll();
    }
}
