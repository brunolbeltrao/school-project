package com.example.schooluser.services;

import com.example.schooluser.entities.User;
import com.example.schooluser.mapper.UserMapper;
import com.example.schooluser.repositories.UserRepository;
import com.example.schooluser.resources.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDTO findUserByEmail(String email){
        return UserMapper.toUserDTO(userRepository.findByEmail(email));
    }
}
