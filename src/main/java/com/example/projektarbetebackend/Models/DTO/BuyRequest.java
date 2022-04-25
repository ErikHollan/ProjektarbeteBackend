package com.example.projektarbetebackend.Models.DTO;

import com.example.projektarbetebackend.Models.Customer;
import com.example.projektarbetebackend.Models.Items;
import lombok.Data;

@Data
public class BuyRequest {

    private Customer customer;
    private Items item;

    public BuyRequest(Customer customer, Items item) {
        this.customer = customer;
        this.item = item;
    }

    public BuyRequest(){}
}
