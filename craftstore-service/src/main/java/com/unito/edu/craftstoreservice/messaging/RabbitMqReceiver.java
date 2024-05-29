package com.unito.edu.craftstoreservice.messaging;

import com.unito.edu.craftstoreservice.model.Comment;
import com.unito.edu.craftstoreservice.model.dto.CommentMaxDto;
import com.unito.edu.craftstoreservice.service.CraftstoreService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitMqReceiver {

    @Autowired
    private CraftstoreService craftstoreService;

    /**
     * When a comment is received from the queue,
     * the craftstore it refers to is updated by adding the comment to its comment list
     * @param comment the received comment
     */
    @RabbitListener(queues = "craftstore.queue")
    public void receive(CommentMaxDto comment) {

        System.out.println("Craftstore microservice received: " + comment);

        craftstoreService.addComment(comment.getCraftstoreId(), new Comment(comment.getId(),
                comment.getUserId(),comment.getUserName(),comment.getPostDate(),comment.getText()));

    }
}
