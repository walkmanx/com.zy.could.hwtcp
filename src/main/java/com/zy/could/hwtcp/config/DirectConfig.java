package com.zy.could.hwtcp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class DirectConfig {
    @Value("${spring.rabbitmq.queuename}")
    private String queueName;

    @Bean
    public Queue hwTcpQueue() {
        return new Queue(queueName);
    }
}