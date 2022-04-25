package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Items, Long> {

}
