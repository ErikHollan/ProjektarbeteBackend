package com.example.projektarbetebackend.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data

public class Item {
    @Id
    @GeneratedValue
    Long id;
    String itemNumber;
    String name;
}
