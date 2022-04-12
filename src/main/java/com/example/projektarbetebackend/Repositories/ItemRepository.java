package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> { }
