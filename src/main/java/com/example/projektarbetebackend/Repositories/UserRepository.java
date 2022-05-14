package com.example.projektarbetebackend.Repositories;

import com.example.projektarbetebackend.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
