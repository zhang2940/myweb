package com.example.myweb.mapper;

import com.example.myweb.pojo.Houses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HousesMapper {
    List<Houses> getAllHouse(@Param("id") Integer id);

    Integer addHouse(Houses houses);
}
