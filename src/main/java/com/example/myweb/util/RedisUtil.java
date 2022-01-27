package com.example.myweb.util;

import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Boolean ifExist(String key){
        Boolean aBoolean = redisTemplate.hasKey(key);
        return aBoolean;
    }
    public void putInHash(String key,Map map){
        redisTemplate.opsForHash().putAll(key,map);
    }
    public Long putInList(String key, List list){
        Long aLong = redisTemplate.opsForList().rightPushAll(key, list);
        return aLong;
    }
    public Object getInHash(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }
    public List getInList(String key){
        return redisTemplate.opsForList().range(key,0,-1);

    }

}
