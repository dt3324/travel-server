package com.dengtao.travel.service.impl;

import com.dengtao.travel.bean.TravelAddressDetail;
import com.dengtao.travel.bean.User;
import com.dengtao.travel.mapper.TravelAddressDetailMapper;
import com.dengtao.travel.mapper.UserMapper;
import com.dengtao.travel.service.IAdviseService;
import com.dengtao.travel.service.ITravelAddressDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelAddressDetailServiceImpl implements ITravelAddressDetailService {
    @Autowired
    private TravelAddressDetailMapper travelAddressDetailMapper;
    @Override
    public TravelAddressDetail select(Long traveladdressId) {
        TravelAddressDetail travelAddressDetail = travelAddressDetailMapper.select(traveladdressId);

        return travelAddressDetail;
    }
}
