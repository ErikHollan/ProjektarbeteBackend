package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")

public class ItemController {

    @Autowired
    private ItemRepository itemRepository;


    //http://localhost:8080/items/add?itemnumber=1&name=shoes
    @PostMapping("/add")
    public String addNewProduct(@RequestBody Items items) {

        itemRepository.save(items);
        return "Item is saved.";
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