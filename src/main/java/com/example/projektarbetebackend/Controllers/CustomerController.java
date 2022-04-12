package com.example.projektarbetebackend.Controllers;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    //http://localhost:8080/customer/add?name=Erik&address=gatan
    @RequestMapping("/add")
    public String addNewUser(@RequestParam String name, @RequestParam(required = false) String address) {
        Customer c = new Customer();
        c.setName(name);
        c.setAddress(address);
        customerRepository.save(c);
        return name + " is saved";
    }

    @RequestMapping("/all")
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @RequestMapping("/getById")
    public Customer getById(@RequestParam long id) {
        return customerRepository.findById(id).get();
    }

}


