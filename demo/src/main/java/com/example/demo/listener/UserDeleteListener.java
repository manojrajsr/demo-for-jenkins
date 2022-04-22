package com.example.demo.listener;

import com.example.demo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class UserDeleteListener {

    @Autowired
    private KafkaService kafkaService;

    @KafkaListener(topics = "${kafka.consumer.deleteUser.queueName}", groupId = "${kafka.consumer.groupId}")
    public void ConsumeDeleteId(String id){
        kafkaService.consumeDeleteUser(Integer.parseInt(id));
    }
}
