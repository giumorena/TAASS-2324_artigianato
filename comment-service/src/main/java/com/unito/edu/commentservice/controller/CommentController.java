package com.unito.edu.commentservice.controller;

import com.unito.edu.commentservice.model.Comment;
import com.unito.edu.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * This API is used to get all comments.
     *
     * @return a list of all comments.
     */
    @GetMapping("/getComments")
    public List<Comment> getAllComments() {

        return commentService.getAllComments();
    }

    /**
     * This API is used to get a comment given its id.
     * @param id the comment id
     * @return the comment with that id
     */
    @GetMapping("/getComment/{id}")
    public Comment getCommentById(@PathVariable int id) {

        return commentService.getCommentById(id);
    }

    /**
     * This method is used to save in the database a comment.
     * Also, a message with the saved comment is sent to the message broker (publish)
     * @param comment the comment to be saved
     * @return the saved comment.
     */
    @PostMapping(value = "/addComment")
    public Comment addComment(@RequestBody Comment comment){

        return commentService.addComment(comment);
    }
}
