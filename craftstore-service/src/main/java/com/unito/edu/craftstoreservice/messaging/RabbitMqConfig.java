package com.unito.edu.craftstoreservice.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class creates the connection to the rabbitMQ server, the Rabbit Template,
 * the direct exchange where comments will be published (if not exists),
 * the queue where Craftstore microservice will be able to receive comments,
 * and the binding between them using a routing key for comments (subscribe)
 */
@Configuration
public class RabbitMqConfig {

    @Value("craftstore.queue")
    private String queue;

    @Value("craft.direct")
    private String exchange;

    @Value("comment")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    //@Value("localhost")
    @Value("rabbitmq")
    private String host;

    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }

    // If the exchange already exists it is not created
    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    // Subscribing for comments
    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(routingKey)
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
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
