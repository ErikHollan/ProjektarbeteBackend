package com.example.projektarbetebackend.Models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn
    private Items item;

    public Orders(Long id, Customer customer, Items item) {
        this.id = id;
        this.customer = customer;
        this.item = item;
    }

    public Orders(){}
}
