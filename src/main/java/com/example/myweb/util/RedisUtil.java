package com.example.myweb.util;

import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtil {

    @Resource
    private  RedisTemplate redisTemplate;

    public  String getValue(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    public void setValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
    public void deleteValue(String key){
        redisTemplate.delete(key);
    }
    public void append(String key,String appendValue){
        redisTemplate.opsForValue().append(key,appendValue);
    }
    public void close(){
        RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
    }


}
