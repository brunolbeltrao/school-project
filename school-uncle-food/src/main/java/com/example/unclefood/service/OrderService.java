package com.example.unclefood.service;

import com.example.unclefood.controller.dto.OrderDTO;
import com.example.unclefood.controller.dto.StudentDTO;
import com.example.unclefood.hexagon.adapter.OrderRepositoryAdapter;
import com.example.unclefood.hexagon.domain.Meal;
import com.example.unclefood.hexagon.domain.Order;
import com.example.unclefood.proxy.ProxyStudentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepositoryAdapter orderRepositoryAdapter;
    @Autowired
    private ProxyStudentFeignClient proxyStudentFeignClient;
    public OrderDTO save(OrderDTO vo) {
        List<Meal> meals = new ArrayList<>();
        vo.getMeals().stream().map(mealDTO -> meals.add(Meal.builder()
                .id(mealDTO.getId())
                .name(mealDTO.getName())
                .build()));
        Order order = Order.builder().meal(meals).studentName(vo.getStudentName()).build();

        StudentDTO studentDTO = proxyStudentFeignClient.findStudentByName(order.getStudentName()).getBody();

        return OrderDTO.build(orderRepositoryAdapter.save(order,studentDTO));
    }

    public List<OrderDTO> Orders() {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = orderRepositoryAdapter.findOrders();

        orders.stream().forEach(order -> {
            orderDTOS.add(OrderDTO.builder()
                    .id(order.getId())
                    .build());
        });
        return orderDTOS;
    }


}
