package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    Iterable<Orders> findAllByCustomerId(long customerId);
}
