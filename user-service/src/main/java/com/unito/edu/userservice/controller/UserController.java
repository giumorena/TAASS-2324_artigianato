package com.unito.edu.userservice.controller;

import com.unito.edu.userservice.model.User;
import com.unito.edu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    /**
     * This API is used to get a user given its id.
     * @param id the user id
     * @return the user with that id
     */
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }

    /**
     * This method is used to save in the database a user.
     * @param user the user to be saved
     * @return the saved user.
     */
    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user){

        return userService.addUser(user);
    }
}
