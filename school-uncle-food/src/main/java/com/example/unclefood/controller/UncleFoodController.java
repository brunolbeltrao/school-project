package com.example.unclefood.controller;

import com.example.unclefood.controller.dto.OrderDTO;
import com.example.unclefood.controller.dto.StudentDTO;
import com.example.unclefood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unclefood")
public class UncleFoodController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO vo){
        return new ResponseEntity<>(orderService.save(vo), HttpStatus.CREATED);
    }

    @GetMapping("getOrders")
    public ResponseEntity<List<OrderDTO>> findOrders(){
        return new ResponseEntity<>(orderService.Orders(), HttpStatus.OK);
    }
}
