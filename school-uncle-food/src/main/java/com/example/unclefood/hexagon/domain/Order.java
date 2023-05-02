package com.example.unclefood.hexagon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private List<Meal> meal;
    private String studentName;
}
