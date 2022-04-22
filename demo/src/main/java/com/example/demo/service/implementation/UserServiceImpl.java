package com.example.demo.service.implementation;

import com.example.demo.repository.UserRepository;
import com.example.demo.user.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String key = "USER";

    @Autowired
    private UserRepository userrepository;


    @Override
    public User getUser(int id){
        //if it is present in redis
        User tmp = (User) redisTemplate.opsForHash().get(key, id);
        if(tmp != null){
            //System.out.println("from redis");
            log.info("from redis");
            return tmp;
        }
        //if it is not in redis

        //System.out.println("from postgres");
        log.info("from postgres");
        User usertmp = userrepository.findById(id);             //fetches from postgres
        redisTemplate.opsForHash().put(key, id, usertmp);       // writing into redis
        return usertmp;

    }

    @Override
    public List<User> getAllUsers(){
        return (List<User>) userrepository.findAll();
        //return (List<User>) redisTemplate.opsForHash().values(key);  //getting all the details from redis
    }

    @Override
    public User createUser(User user){
        userrepository.save(user);                                   //writing into postgres
        redisTemplate.opsForHash().put(key, user.getId(),user);      //writing into redis
        return user;
    }

    @Override
    public String deleteUser(int id) {
        /*userrepository.delete(id);
        redisTemplate.opsForHash().delete(key, id);
        System.out.println("deleted succesfully");*/
        User tmp =(User) getUser(id);
        System.out.println(tmp);
        if(tmp == null){
            return "Id does not exists";
        }
        redisTemplate.opsForHash().delete(key,id);
        userrepository.delete(tmp);
        return "User deleted";
    }
}
