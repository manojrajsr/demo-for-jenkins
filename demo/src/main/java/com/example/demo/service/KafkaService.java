package com.example.demo.service;

import com.example.demo.user.User;

public interface KafkaService {
    String publishNewUser(User user);
    void consumeNewUser (String user);

    String publishDeleteUser(int id);
    void consumeDeleteUser (int id);
}
