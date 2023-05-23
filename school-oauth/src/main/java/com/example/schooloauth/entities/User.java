package com.example.schooloauth.entities;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
