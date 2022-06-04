package com.comodo.todoapi.service;

import com.comodo.todoapi.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQ {

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot-queue";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(Todo todo) {
        rabbitTemplate.convertAndSend(topicExchangeName, queueName, todo);
        log.info("send rabbit {} ",todo);
    }

}