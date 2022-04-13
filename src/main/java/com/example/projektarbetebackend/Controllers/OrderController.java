package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/orders")

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/all")
    public Iterable<Orders> getAllOrders(){
        return orderRepository.findAll();
    }


    @RequestMapping ("/orderbycustomerid")
    public Orders getOrdersById(long id){
        return orderRepository.findById(id).get();
    }

}
