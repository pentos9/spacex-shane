package com.buzz.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "spring-boot")
    public void processMessage(String message) {
        logger.info("[X] Receive#topic.message:{}", message);
    }

    @RabbitListener(queues = "spring-boot-messages")
    public void topics(String message) {
        logger.info("[X] Receive#topics: {}", message);
    }
}
