package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.Item;
import com.example.projektarbetebackend.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
