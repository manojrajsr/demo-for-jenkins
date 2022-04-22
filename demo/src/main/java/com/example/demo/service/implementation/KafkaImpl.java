package com.example.demo.service.implementation;

import com.example.demo.service.KafkaService;
import com.example.demo.service.UserService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class KafkaImpl implements KafkaService {

    Logger log = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //private static final String createUser = "${kafka.consumer.createUser.queueName}";
    private static final String createUser = "New_User";
    //private static final String deleteUser = "${kafka.consumer.deleteUser.queueName}";
    private static final String deleteUser = "Delete_User";

    @Override
    public String publishNewUser(User user){
        kafkaTemplate.send(createUser, user.toString());
        return "published";
    }

    @Override
    public void consumeNewUser(String user){
        User tmp = User.toUser(user);
        log.info("successfully converted into user");
        try{
            userService.createUser(tmp);
            log.info("saved successfully into postgres and redis");
        } catch (Exception e){
            log.info("Could not save the details into the database..."+e);
        }
    }

    @Override
    public String publishDeleteUser(int id){
        kafkaTemplate.send(deleteUser, Integer.toString(id));
        return "published for deletion";
    }


    @Override
    public void consumeDeleteUser(int id){
        userService.deleteUser(id);
    }


}
