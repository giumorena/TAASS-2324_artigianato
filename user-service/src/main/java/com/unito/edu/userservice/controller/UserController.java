package com.unito.edu.userservice.controller;

import com.unito.edu.userservice.model.Comment;
import com.unito.edu.userservice.model.User;
import com.unito.edu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This API is used to get all users.
     *
     * @return a list of all users.
     */
    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers() {

        return userService.getAllUsers();
    }

    /**
     * This API is used to get a user given his id.
     * @param id the user id
     * @return the user with that id
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }

    /**
     * This API is used to get a user given his email.
     * @param email the user's email
     * @return the user with that email
     */
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

        return userService.getUserByEmail(email);
    }

    /**
     * This API is used to get comments posted by a user given his id, sorted in descending order by post date.
     * @param id the user id
     * @return comments posted by the user with that id, sorted in descending order by post date
     */
    @GetMapping("/getUserComments/{id}")
    public ResponseEntity<?> getUserCommentsById(@PathVariable int id) {

        return userService.getUserCommentsById(id);
    }

    /**
     * This method is used to save in the database a user.
     * @param user the user to be saved
     * @return the saved user.
     */
    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){

        return userService.addUser(user);
    }
}
