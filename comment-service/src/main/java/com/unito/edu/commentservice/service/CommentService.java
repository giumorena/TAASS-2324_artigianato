package com.unito.edu.commentservice.service;

import com.unito.edu.commentservice.messaging.RabbitMqSender;
import com.unito.edu.commentservice.model.Comment;
import com.unito.edu.commentservice.repository.CommentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getAllComments() {

        List<Comment> comments = commentRepository.findAll();

        if(comments.size()>0){
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(comments, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get a comment given its id.
     * @param id the comment id
     * @return the comment with that id
     */
    public ResponseEntity<?> getCommentById(int id) {

        Optional<Comment> comment = commentRepository.findById(id);

        if(comment.isPresent()){
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to save in the database a comment.
     * Also, a message with the saved comment is sent to the message broker (publish)
     * @param comment the comment to be saved
     * @return the saved comment.
     */
    public ResponseEntity<?> addComment(Comment comment){

        Comment commentSaved;
        try {
            commentSaved = commentRepository.save(comment);
        }
        catch (Exception e){
            System.out.println("Comment microservice has NOT SAVED and thus NOT SENT comment: " + comment);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        rabbitMqSender.send(commentSaved);

        return new ResponseEntity<>(commentSaved,HttpStatus.OK);
    }
}
