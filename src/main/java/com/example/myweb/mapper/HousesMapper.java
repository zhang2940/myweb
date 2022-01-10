package com.example.myweb.mapper;

import com.example.myweb.pojo.Houses;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HousesMapper {
    List<Houses> getAllHouse(String id);

    Integer addHouse(Houses houses);
}
