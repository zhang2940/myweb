package com.example.myweb.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
//@EnableCaching//启动配置注解
public class RedisConfig {

//    /**
//     * redis配置
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
////        配置redis连接工厂
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
////        使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om=new ObjectMapper();
////        指定要序列化的域，类必须是非final修饰的
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
////      值采用json序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
////        使用StringRedisSerializer来反序列化和序列化redis的值
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        设置hashkey 和hashvalue的序列化方式
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//
//
//    }



}
