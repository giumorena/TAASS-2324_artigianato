package com.unito.edu.userservice.messaging;

import com.unito.edu.userservice.model.Comment;
import com.unito.edu.userservice.model.dto.CommentMaxDto;
import com.unito.edu.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitMqReceiver {

    @Autowired
    private UserService userService;

    /**
     * When a comment is received from the queue,
     * the user it refers to is updated by adding the comment to its comment list
     * @param comment the received comment
     */
    @RabbitListener(queues = "user.queue")
    public void receive(CommentMaxDto comment) {

        System.out.println("User microservice received: " + comment);

        userService.addComment(comment.getUserId(), new Comment(comment.getId(),
                comment.getCraftstoreId(),comment.getCraftstoreName(),comment.getText()));

    }
}
