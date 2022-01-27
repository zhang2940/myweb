package com.example.myweb.service;

import com.example.myweb.pojo.Houses;
import com.example.myweb.pojo.Order;
import com.example.myweb.vo.PreOrders;

import java.util.List;

public interface OrderService {

    List<Houses> getMyOrder(Integer userId);

    boolean preOrders(PreOrders preOrders);

    Integer getHouses(PreOrders preOrders);

    List getMyWishOrder(String userId);

    List<Houses> getMyPreOrder(List myWishOrder);
}
