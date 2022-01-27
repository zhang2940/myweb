package com.example.myweb.service;

import com.example.myweb.mapper.OrderMapper;
import com.example.myweb.pojo.Houses;
import com.example.myweb.pojo.Order;
import com.example.myweb.util.RedisUtil;
import com.example.myweb.vo.PreOrders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private OrderMapper orderMapper;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Houses> getMyOrder(Integer userId) {
        return orderMapper.getMyOrder(userId);
    }

    @Override
    public boolean preOrders(PreOrders preOrders) {
        Long aLong = redisUtil.putInList(String.valueOf(preOrders.getUserId()), preOrders.getHouseId());
        if (aLong!=0&&aLong>0){
            return true;
        }
        return false;
    }

    @Override
    public Integer getHouses(PreOrders preOrders) {
        Integer houses = orderMapper.getHouses(preOrders);
        if (houses!=0&& houses>0){
            redisUtil.deleteValue(preOrders.getUserId().toString());
        }
        return houses;
    }

    @Override
    public List getMyWishOrder(String userId) {
        List inHash = redisUtil.getInList(userId);
        logger.info("购物信息：{}",inHash);
        return inHash;
    }

    @Override
    public List<Houses> getMyPreOrder(List myWishOrder) {
        List<Houses> myPreOrder = orderMapper.getMyPreOrder(myWishOrder);
        logger.info("购物车列表：{}" ,myPreOrder);
        return myPreOrder;
    }
}
