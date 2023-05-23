package com.example.schooloauth.services;

import com.example.schooloauth.entities.User;
import com.example.schooloauth.feingclients.UserFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email){
        User user = userFeignClient.findByEmail(email).getBody();
        if (user == null){
            logger.error("Email not found "+ email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found "+ email);
        return  user;
    }
}
