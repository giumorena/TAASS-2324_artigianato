package com.unito.edu.commentservice.messaging;

import com.unito.edu.commentservice.model.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is used to send messages to the direct exchange
 */
@Component
public class RabbitMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("craft.direct")
    private String exchange;

    @Value("comment")
    private String routingKey;

    /**
     * This method sends a message containing a comment to the direct exchange
     * with the routing key for comments (publish)
     * @param comment the comment to be sent
     */
    public void send(Comment comment){

        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, comment);
            System.out.println("Comment microservice sent: " + comment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
