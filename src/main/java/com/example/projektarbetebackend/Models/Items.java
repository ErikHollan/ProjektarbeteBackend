package com.example.projektarbetebackend.Models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data

public class Items {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String itemNumber;
    private String name;


    public Items(long id, String itemNumber, String name) {
        this.id = id;
        this.itemNumber = itemNumber;
        this.name = name;
    }
    public Items(){}
}

