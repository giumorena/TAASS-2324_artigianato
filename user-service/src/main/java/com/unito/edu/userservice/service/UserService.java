package com.unito.edu.userservice.service;

import com.unito.edu.userservice.model.Comment;
import com.unito.edu.userservice.model.User;
import com.unito.edu.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to get all users.
     *
     * @return a list of all users.
     */
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    /**
     * This method is used to get a user given its id.
     * @param id the user id
     * @return the user with that id
     */
    public User getUserById(int id) {

        return userRepository.findById(id).orElse(null);
    }

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
