package com.example.demo.controller;

import com.example.demo.service.KafkaService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("kafka")
public class    KafkaController {

    Logger log = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaService kafkaService;

    @RequestMapping(method = RequestMethod.POST, value="/publish")
    public String post(@RequestBody User user){
        log.info("Publishing the message to kafka");
        return kafkaService.publishNewUser(user);}

    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public String post(@PathVariable int id){
        log.info("Publishing the message to kafka");
        return kafkaService.publishDeleteUser(id);}
}
