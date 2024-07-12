package com.unito.edu.userservice.service;

import com.unito.edu.userservice.model.Comment;
import com.unito.edu.userservice.model.User;
import com.unito.edu.userservice.repository.CommentRepository;
import com.unito.edu.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * This method is used to get all users.
     *
     * @return a list of all users.
     */
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    /**
     * This method is used to get a user given his id.
     * @param id the user id
     * @return the user with that id
     */
    public User getUserById(int id) {

        return userRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to get a user given his email.
     * @param email the user's email
     * @return the user with that email
     */
    public ResponseEntity<?> getUserByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get comments posted by a user given his id, sorted in descending order by post date.
     * @param id the user id
     * @return comments posted by the user with that id, sorted in descending order by post date
     */
    public List<Comment> getUserCommentsById(int id) {
        return commentRepository.findOrderedCommentsByUserId(id);
    }

    /*
    public List<Comment> getUserCommentsById(int id) {

        Optional<User> user= userRepository.findById(id);

        //copy of the comment list
        List<Comment> list = new ArrayList<>( user.get().getCommentList().size() );
        for ( Comment comment : user.get().getCommentList() ) {
            list.add( new Comment(comment.getId(),comment.getCraftstoreId(),comment.getCraftstoreName(),comment.getPostDate(),comment.getText()));
        }

        //list sorting
        list.sort(Comparator.comparing(Comment::getPostDate).reversed());

        return list;
    }
    */

    /**
     * This method is used to save in the database a user.
     * @param user the user to be saved
     * @return the saved user.
     */
    public User addUser(User user){

        return userRepository.save(user);
    }

    /**
     * This method is used to add a comment to a user comment list.
     * @param id the user id
     * @param comment the comment to be added
     * @return the updated user
     */
    public User addComment(int id, Comment comment){

        Optional<User> user= userRepository.findById(id);
        user.get().getCommentList().add(comment);

        return userRepository.save(user.get());
    }
}
