package com.example.demo.listener;

import com.example.demo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class UserCreationListener {

    Logger log = LoggerFactory.getLogger(UserCreationListener.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String key = "USER";

    @Autowired
    private KafkaService kafkaService;

    @KafkaListener(topics = "${kafka.consumer.createUser.queueName}", groupId = "${kafka.consumer.groupId}",containerFactory = "userKafkaListenerFactory")
    public void ConsumeNewUser(String user) {
        log.info("Consuming New User details");
        kafkaService.consumeNewUser(user);
    }
}
