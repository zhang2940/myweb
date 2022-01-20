package com.example.myweb.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RabbitMQUtil {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Boolean sendMsg(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("msg","this is the first message");
        rabbitTemplate.convertSendAndReceive("my_web_exchange","myweb_queue",stringStringHashMap);
        return true;
    }





}
