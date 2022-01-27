package com.example.myweb.mapper;

import com.example.myweb.pojo.Houses;
import com.example.myweb.vo.PreOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

     List<Houses> getMyOrder(Integer userId);

    Integer getHouses(PreOrders preOrders);

    List<Houses> getMyPreOrder(@Param("myWishOrder") List myWishOrder);
}
