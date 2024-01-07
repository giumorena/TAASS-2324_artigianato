package com.unito.edu.commentservice.service;

import com.unito.edu.commentservice.model.Comment;
import com.unito.edu.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

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
     * @param comment the comment to be saved
     * @return the saved comment.
     */
    public Comment addComment(Comment comment){

        return commentRepository.save(comment);
    }
}
