package com.example.projektarbetebackend.Controllers;
import com.example.projektarbetebackend.Models.User;
import com.example.projektarbetebackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created";
    }
    @RequestMapping("/all")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
