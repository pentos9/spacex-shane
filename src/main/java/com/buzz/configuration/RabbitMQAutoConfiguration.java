package com.buzz.configuration;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQAutoConfiguration {
    public static final String topicExchangeName = "spring-boot-exchange";
    public static final String queueName = "spring-boot";
    public static final String routingKey = "topic.message.#";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("spring-boot-messages");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with("topic.message");
    }

    @Bean
    Binding binding2(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages()).to(exchange).with("topic.#");
    }

}
