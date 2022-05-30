package com.example.projektarbetebackend.Controllers;


import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/all")
    public Iterable<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    @RequestMapping("/customerId")
    public Iterable<Orders> getOrdersByCustId(@RequestParam Long custId){
        return orderRepository.findAllByCustomerId(custId);
    }

    @PostMapping("/buy")
    public String newOrder(@RequestBody Orders order) {

        Orders o = new Orders();

        o.setCustomer(order.getCustomer());
        o.setItem(order.getItem());

        orderRepository.save(o);
        return "Order has been placed.";
    }


}
