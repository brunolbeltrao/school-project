package com.example.unclefood;

import com.example.unclefood.controller.dto.MealDTO;
import com.example.unclefood.controller.dto.OrderDTO;
import com.example.unclefood.repository.MealEntity;
import com.example.unclefood.repository.MealRepositoryJpa;
import com.example.unclefood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@LoadBalancerClient(name = "school")
public class UncleFoodApplication implements CommandLineRunner {

    @Autowired
    private MealRepositoryJpa mealRepositoryJpa;
    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(UncleFoodApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MealEntity meal = MealEntity.builder().name("Crisps and cream").build();
        MealEntity meal2 = MealEntity.builder().name("Cheese").build();
        MealEntity meal3 = MealEntity.builder().name("Feijoada").build();
        Set<MealEntity> meals = new HashSet<>(Arrays.asList(meal,meal2,meal3));

        mealRepositoryJpa.saveAll(meals);

        Optional<MealEntity> meal4  = mealRepositoryJpa.findById(1L);
        Optional<MealEntity> meal5  = mealRepositoryJpa.findById(2L);
        MealEntity mealEntity = meal4.get();
        MealEntity mealEntity2 = meal5.get();

        MealDTO mealDTO = MealDTO.builder().id(mealEntity.getId()).name(mealEntity.getName()).build();
        MealDTO mealDTO2 = MealDTO.builder().id(mealEntity2.getId()).name(mealEntity2.getName()).build();

        List<MealDTO> meals2 = new ArrayList<>(Arrays.asList(mealDTO,mealDTO2));

        OrderDTO orderDTO = OrderDTO.builder().meals(meals2).studentName("Elon Musk").build();

        orderService.save(orderDTO);

        OrderDTO orderDTO2 = OrderDTO.builder().meals(meals2).studentName("Bill").build();

        orderService.save(orderDTO2);

        OrderDTO orderDTO3 = OrderDTO.builder().meals(meals2).studentName("Jon").build();

        orderService.save(orderDTO3);

    }
}
