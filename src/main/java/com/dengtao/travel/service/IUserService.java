package com.dengtao.travel.service;

import com.dengtao.travel.bean.User;

public interface IUserService {
    void save(User user);

    User findByUser(User user);
}
