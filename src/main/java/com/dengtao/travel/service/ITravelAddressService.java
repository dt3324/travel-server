package com.dengtao.travel.service;

import com.dengtao.travel.bean.TravelAddress;

import java.util.List;

public interface ITravelAddressService {
    List<TravelAddress> search(String keyword);

    List<TravelAddress> searchAll();
}
