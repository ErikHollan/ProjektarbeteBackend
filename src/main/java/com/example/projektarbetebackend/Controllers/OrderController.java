package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.DTO.BuyRequest;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Iterator;
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

    @RequestMapping("/customerId")
    public Iterable<Orders> getOrdersByCustId(@RequestParam Long custId){


        List list = new ArrayList();
        Iterator<Orders> it = orderRepository.findAll().iterator();

        while(it.hasNext()){
            Orders orders = it.next();
            if (orders.getCustomer().getId()==custId){
               list.add(orders);
            }
        }

       return list;
    }


}
