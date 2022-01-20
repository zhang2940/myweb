package com.example.myweb.controller;

import com.example.myweb.pojo.Order;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.OrderService;
import com.example.myweb.vo.PreOrders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/get_my_orders")
    public ResultVO getMyOrders(Integer userId){
        List<Order> myOrder = orderService.getMyOrder(userId);
        return ResultVO.success(myOrder);
    }
    @RequestMapping("/preOrder")
    public ResultVO preOrders(@RequestBody PreOrders preOrders){
        if (preOrders.getUserId()==null){
            return ResultVO.error("上传数据异常，请检查是否上传");
        }
        if (StringUtils.isEmpty(preOrders)){
            return ResultVO.error("数据为空，请检查是否上传");
        }
        if (StringUtils.isEmpty(preOrders.getHouseId())){
            return ResultVO.error("房子id为空，请检查是否上传");
        }
        orderService.preOrders(preOrders);
        return ResultVO.success();
    }


}
