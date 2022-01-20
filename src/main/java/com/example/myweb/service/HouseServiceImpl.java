package com.example.myweb.service;

import com.example.myweb.mapper.HousesMapper;
import com.example.myweb.pojo.Houses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    @Resource
    private HousesMapper housesMapper;
    @Override
    public List<Houses> getAllHouse(Integer id) {
        return housesMapper.getAllHouse(id);
    }

    @Override
    public Integer addHouse(Houses houses) {
        return housesMapper.addHouse(houses);
    }
}
