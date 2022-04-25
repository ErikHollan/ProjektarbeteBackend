package com.example.projektarbetebackend.Models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Orders {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToMany
    @JoinTable
    private List<Items> items;
}
