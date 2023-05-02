package com.example.unclefood.hexagon.adapter;


import com.example.unclefood.controller.dto.StudentDTO;
import com.example.unclefood.hexagon.domain.Meal;
import com.example.unclefood.hexagon.domain.Order;
import com.example.unclefood.proxy.ProxyStudentFeignClient;
import com.example.unclefood.repository.MealEntity;
import com.example.unclefood.repository.OrderEntity;
import com.example.unclefood.repository.OrderRepository;
import com.example.unclefood.repository.OrderRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;


    public OrderRepositoryAdapter(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }


    public Order save(Order order,StudentDTO studentDTO) {
        Set<MealEntity> mealEntitys = order.getMeal().stream().map(meal ->
                MealEntity.builder().id(meal.getId()).name(meal.getName()).build()).collect(Collectors.toSet());

        OrderEntity orderEntity = new OrderEntity(order.getId(),studentDTO.getId(),mealEntitys);;
        orderRepositoryJpa.save(orderEntity);
        return order;
    }

    public List<Order> findOrders() {
        return buildListOrder(orderRepositoryJpa.findAll());
    }

    private List<Order> buildListOrder(List<OrderEntity> orderEntitys) {
        List<Order> orders =  new ArrayList<>();

        List<Meal> meals = new ArrayList<>();

        for (int i = 0; i < orderEntitys.size() ; i++) {
            List<Meal> listMeal = new ArrayList<Meal>();

            Order order = new Order(orderEntitys.get(i).getId(),listMeal,"");
            orders.add(order);
        }

        return orders;
    }
}

