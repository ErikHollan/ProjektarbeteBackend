package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Models.Items;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/add")
    public Customer addNewProduct(@RequestBody Customer customer) {

        Customer c  = customerRepository.save(customer);
        return c;
    }


    //http://localhost:8080/customer/all
    @RequestMapping("/all")
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //getById
    //http://localhost:8080/customer/1
    @RequestMapping("/{id}")
    public Customer getById(@PathVariable("id")long id) {
        return customerRepository.findById(id).get();
    }
}


