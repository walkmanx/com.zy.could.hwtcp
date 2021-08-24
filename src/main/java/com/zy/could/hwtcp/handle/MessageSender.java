package com.zy.could.hwtcp.handle;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class MessageSender {

    @Value("${spring.rabbitmq.queuename}")
    private String queueName;

    @Value("${companyId}")
    private String companyId;

    @Autowired
    private AmqpTemplate rabbitTemplate;
     
    public void send(String msg){
        rabbitTemplate.convertAndSend(queueName + "_" + companyId, msg);
    }
}