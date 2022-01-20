package com.example.myweb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final String ROUTING_NAME="myweb_queue";

    @Bean
    public Queue queue(){
        return new Queue("queue",true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("my_web_exchange");
    }

    @Bean
    public Binding binding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_NAME);
    }
}
