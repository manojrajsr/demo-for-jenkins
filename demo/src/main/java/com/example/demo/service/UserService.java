package com.example.demo.service;

import com.example.demo.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    public User getUser(int id);
    public List<User> getAllUsers();
    public User createUser(User user);
    public String deleteUser(int id);
}
