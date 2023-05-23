package com.example.schooluser.mapper;

import com.example.schooluser.entities.User;
import com.example.schooluser.resources.dto.UserDTO;

public class UserMapper {
    private UserMapper(){

    }

    public static User toUser(UserDTO user){
        return User.builder().id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public static UserDTO toUserDTO(User user){
        return UserDTO.builder().id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
