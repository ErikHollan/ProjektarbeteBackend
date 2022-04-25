package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.DTO.BuyRequest;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/items")

public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //http://localhost:8080/items/add?itemnumber=1&name=shoes
    @PostMapping("/add")
    public String addNewProduct(@RequestBody Items items) {

        itemRepository.save(items);
        return "Item is saved.";
    }


    @PostMapping("/buy")
    public Orders newOrder(@RequestBody BuyRequest buyRequest) {

        Orders o = new Orders();

        o.setCustomer(customerRepository.findById(buyRequest.getCustomerId()).get());
        o.setItems(itemRepository.findById(buyRequest.getItemId()).stream().collect(Collectors.toList()));

        orderRepository.save(o);
        System.out.println(buyRequest.getCustomerId());
        System.out.println(buyRequest.getItemId());
        return o;
    }


    //http://localhost:8080/items/all
    @RequestMapping("/all")
    public Iterable<Items> getAllProducts(){
        return itemRepository.findAll();
    }

    //http://localhost:8080/item/3
    @RequestMapping ("/{id}")
    public Items getitemById(@PathVariable("id")long id){
        return itemRepository.findById(id).get();
    }
}