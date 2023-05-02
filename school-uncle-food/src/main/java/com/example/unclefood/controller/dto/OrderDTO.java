package com.example.unclefood.controller.dto;

import com.example.unclefood.hexagon.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String studentName;
    private List<MealDTO> meals;


    public static OrderDTO build(Order order) {
        OrderDTO dto =new OrderDTO();
        List<MealDTO> mealDTO = order.getMeal().stream().map(meal ->
                MealDTO.builder().
                        id(meal.getId()).
                        name(meal.getName())
                        .build())
                .collect(Collectors.toList());

        dto.builder()
                .id(order.getId())
                .studentName(order.getStudentName())
                .meals(mealDTO)
                .build();

        return dto;
    }

}
