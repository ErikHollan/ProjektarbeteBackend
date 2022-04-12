package com.example.projektarbetebackend.Models;

import javax.persistence.*;
import java.util.List;

public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToMany
    @JoinTable
    private List<Item> items;

}
