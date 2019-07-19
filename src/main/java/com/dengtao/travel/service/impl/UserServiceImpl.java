package com.dengtao.travel.service.impl;

import com.dengtao.travel.bean.User;
import com.dengtao.travel.mapper.UserMapper;
import com.dengtao.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        String openId = user.getOpenId();
        User findUser=null;
        if(openId!=null && openId!=""){
            findUser = userMapper.findByOpenId(openId);
        }
        if(findUser!=null){
            userMapper.update(user);
        }else {
            userMapper.save(user);
        }
    }

    @Override
    public User findByUser(User user) {
        User byOpenId = userMapper.findByOpenId(user.getOpenId());
        return byOpenId;
    }
}


