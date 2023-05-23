package com.example.schooloauth.entities;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    private Long id;
    private String name;
}
