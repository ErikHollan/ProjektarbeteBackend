package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Models.Orders;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import com.example.projektarbetebackend.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/add")
    public String addNewProduct(@RequestParam String itemnumber, @RequestParam String name) {
        Items i = new Items();
        i.setItemNumber(itemnumber);
        i.setName(name);

        itemRepository.save(i);
        return "Product " + itemnumber + ": " + name + " - " + " has been added";
    }

    //http://localhost:8080/items/buy?customer=1&itemid=13
    @RequestMapping("/buy")
    public String addNewOrder(@RequestParam Long customer
            ,@RequestParam Long itemid) {
        Orders o = new Orders();
        o.setCustomer(customerRepository.findById(customer).get());
        o.setItems(itemRepository.findById(itemid).stream().collect(Collectors.toList()));
        orderRepository.save(o);
        return "order has been placed";
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