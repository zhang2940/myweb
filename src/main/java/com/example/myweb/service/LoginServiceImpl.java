package com.example.myweb.service;

import com.example.myweb.mapper.LoginMapper;
import com.example.myweb.pojo.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private LoginMapper loginMapper;

    @Override
    public User login(String username) {
        return loginMapper.login(username);
    }

    @Override
    public Integer updatePwd(User user) {
        return loginMapper.updatePwd(user);
    }

    @Override
    public Integer region(User user) {
        User users=new User(new Md5Hash(user.getUsername()).toString(),new Md5Hash(user.getPassword()).toString());
        return loginMapper.region(users);
    }
}
