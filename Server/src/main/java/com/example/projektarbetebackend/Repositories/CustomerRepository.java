package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Long> { }
