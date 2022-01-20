package com.example.myweb.service;

import com.example.myweb.pojo.Houses;
import com.example.myweb.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HouseService {
    List<Houses> getAllHouse(Integer id);
    Integer addHouse(Houses houses);
}
