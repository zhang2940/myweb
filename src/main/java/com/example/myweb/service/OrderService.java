package com.example.myweb.service;

import com.example.myweb.pojo.Order;
import com.example.myweb.vo.PreOrders;

import java.util.List;

public interface OrderService {

    List<Order> getMyOrder(Integer userId);

    boolean preOrders(PreOrders preOrders);
}
