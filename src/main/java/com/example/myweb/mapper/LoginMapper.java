package com.example.myweb.mapper;

import com.example.myweb.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    User login(String username);

    Integer updatePwd(User user);

    Integer region(User user);
}
