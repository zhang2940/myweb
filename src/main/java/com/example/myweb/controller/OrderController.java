package com.example.myweb.controller;

import com.example.myweb.pojo.Houses;
import com.example.myweb.result.ResultVO;
import com.example.myweb.service.OrderService;
import com.example.myweb.vo.PreOrders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/get_my_orders",method = RequestMethod.GET)
    public ResultVO getMyOrders(Integer userId){
        logger.info("获取订单信息");
        List<Houses> myOrder = orderService.getMyOrder(userId);
        return ResultVO.success(myOrder);
    }
    @RequestMapping("/preOrder")
    public ResultVO preOrders(@RequestBody PreOrders preOrders){
        logger.info("预购房，加入redis");
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
    @GetMapping("/getMyWishOrder/{userId}")
    public ResultVO getMyWishOrder(@PathVariable String userId){
        logger.info("获取欲购房订单");
        List myWishOrder = orderService.getMyWishOrder(userId);
        List<Houses> myPreOrder = orderService.getMyPreOrder(myWishOrder);
        return ResultVO.success();
    }
    @RequestMapping("/buy")
    public ResultVO buy(@RequestBody PreOrders preOrders){
        orderService.getHouses(preOrders);
        return ResultVO.success();
    }


}
