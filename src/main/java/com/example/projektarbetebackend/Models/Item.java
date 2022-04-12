package com.example.projektarbetebackend.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Item {

    @Id
    @GeneratedValue

    Long id;
    String itemNumber;
    String name;

}
