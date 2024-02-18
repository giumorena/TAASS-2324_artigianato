package com.unito.edu.commentservice.messaging;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class creates the connection to the rabbitMQ server,
 * the direct exchange where comments will be published (if not exists)
 * and the Rabbit Template used to send messages with comments
 */
@Configuration
public class RabbitMqConfig {

    //@Value("localhost")
    @Value("rabbitmq")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Value("craft.direct")
    private String exchange;

    // If the exchange already exists it is not created
    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
