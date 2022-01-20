package com.example.myweb.service;

import com.example.myweb.pojo.Order;
import com.example.myweb.util.RedisUtil;
import com.example.myweb.vo.PreOrders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private RedisUtil redisUtil;


    @Override
    public List<Order> getMyOrder(Integer userId) {
        return null;
    }

    @Override
    public boolean preOrders(PreOrders preOrders) {
        Map<Object, Object> preOrdersMap = new HashMap<>();
        preOrdersMap.put(preOrders.getUserId(),preOrders.getHouseId());
        redisUtil.putInHash(String.valueOf(preOrders.getUserId()),preOrdersMap);
        return true;
    }
}
