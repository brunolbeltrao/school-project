package com.example.unclefood.repository;

import com.example.unclefood.hexagon.domain.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_student;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="ORDER_MEAL",
            joinColumns = @JoinColumn(name="meal_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    private Set<MealEntity> meal = new HashSet<>();
}
