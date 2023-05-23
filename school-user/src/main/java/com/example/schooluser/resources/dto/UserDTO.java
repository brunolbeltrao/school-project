package com.example.schooluser.resources.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO  {
    private Long id;
    private String name;
    private String email;
    private String password;
}

