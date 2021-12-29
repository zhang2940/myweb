package com.example.myweb.service;

import com.example.myweb.pojo.User;

public interface LoginService {
    User login(User user);

    Integer updatePwd(User user);
}
