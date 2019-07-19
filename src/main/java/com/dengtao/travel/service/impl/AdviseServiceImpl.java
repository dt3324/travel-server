package com.dengtao.travel.service.impl;

import com.dengtao.travel.bean.Advise;
import com.dengtao.travel.bean.User;
import com.dengtao.travel.mapper.AdviseMapper;
import com.dengtao.travel.mapper.UserMapper;
import com.dengtao.travel.service.IAdviseService;
import com.dengtao.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviseServiceImpl implements IAdviseService {
    @Autowired
    private AdviseMapper adviseMapper;
    @Override
    public void save(Long userId, String advise) {
        Advise advise1 = new Advise();
        advise1.setAdvise(advise);
        advise1.getUser().setId(userId);
        advise1.setAdviseTime(System.currentTimeMillis());
        adviseMapper.save(advise1);
    }

    @Override
    public List<Advise> select() {
        List<Advise> select = adviseMapper.select();
        return select;
    }
}
