package com.example.unclefood.repository;

import com.example.unclefood.controller.dto.StudentDTO;
import com.example.unclefood.hexagon.domain.Order;

public interface OrderRepository {
    Order save(Order order, StudentDTO studentDTO);
}

