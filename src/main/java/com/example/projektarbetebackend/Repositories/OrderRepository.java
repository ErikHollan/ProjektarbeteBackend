package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.DTO.BuyRequest;
import com.example.projektarbetebackend.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    Iterable<BuyRequest> findAllByCustomerId(long customerId);
}
