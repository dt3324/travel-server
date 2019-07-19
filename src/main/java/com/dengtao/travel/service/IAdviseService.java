package com.dengtao.travel.service;

import com.dengtao.travel.bean.Advise;

import java.util.List;

public interface IAdviseService {
    void save(Long userId, String advise);

    List<Advise> select();
}
