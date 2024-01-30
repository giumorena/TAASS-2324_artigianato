package com.unito.edu.commentservice.service;

import com.unito.edu.commentservice.messaging.RabbitMqSender;
import com.unito.edu.commentservice.model.Comment;
import com.unito.edu.commentservice.repository.CommentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RabbitMqSender rabbitMqSender;

    /**
     * This method is used to get all comments.
     *
     * @return a list of all comments.
     */
    public List<Comment> getAllComments() {

        return commentRepository.findAll();
    }

    /**
     * This method is used to get a comment given its id.
     * @param id the comment id
     * @return the comment with that id
     */
    public Comment getCommentById(int id) {

        return commentRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to save in the database a comment.
     * Also, a message with the saved comment is sent to the message broker (publish)
     * @param comment the comment to be saved
     * @return the saved comment.
     */
    public Comment addComment(Comment comment){

        Comment commentSaved=commentRepository.save(comment);

        rabbitMqSender.send(commentSaved);

        return commentSaved;
    }
}
