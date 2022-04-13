package com.example.projektarbetebackend.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToMany
    @JoinTable
    private List<Items> items;

}
