package com.buzz.mq;

import com.buzz.configuration.RabbitMQAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Sender {

    private Logger logger = LoggerFactory.getLogger(Sender.class);

    private static final String queueName = "spring-boot";
    private static final String routingKey = "topic.message";
    private static final String exchange = "spring-boot-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String message = dateFormat.format(new Date()) + " - Topic Transfer";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        logger.info("[X] Send '" + message + "'");
    }
}
