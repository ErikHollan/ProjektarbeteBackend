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
    Long id;
    String itemNumber;
    String name;


    public Items(long id, String itemNumber, String name) {
        this.id = id;
        this.itemNumber = itemNumber;
        this.name = name;
    }
    public Items(){}
}

