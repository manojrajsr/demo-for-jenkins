package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userservice;

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        log.info("Getting all the user");
        return userservice.getAllUsers();
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        log.info("Getting "+id+" user");
        return userservice.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/user")
    public User createUser(@RequestBody User user){
        log.info("Creating New User");
        return userservice.createUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public String deleteUser(@PathVariable int id){
        log.info("Deleting "+id+" user");
        return userservice.deleteUser(id);
    }
}