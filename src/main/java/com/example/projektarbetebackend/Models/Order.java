package com.example.projektarbetebackend.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

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
