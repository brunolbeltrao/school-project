package com.example.unclefood.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class StudentDTO {
    private Long id;
    private String name;
}
