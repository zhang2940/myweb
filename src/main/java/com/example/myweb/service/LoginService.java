package com.example.myweb.service;

import com.example.myweb.pojo.User;

public interface LoginService {
    User login(String username);

    Integer updatePwd(User user);

    Integer region(User user);
}
