package com.example.myweb.service;

import com.example.myweb.mapper.LoginMapper;
import com.example.myweb.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private LoginMapper loginMapper;

    @Override
    public User login(User user) {
        return loginMapper.login(user);
    }

    @Override
    public Integer updatePwd(User user) {
        return loginMapper.updatePwd(user);
    }
}
