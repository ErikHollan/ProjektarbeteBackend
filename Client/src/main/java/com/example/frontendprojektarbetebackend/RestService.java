package com.example.frontendprojektarbetebackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RestService {

    private final RestTemplate restTemplate;

    @Value( "${webshop.api}" )
    private String baseUrl;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/all")
    public String getAll(Model model) {
        String url = baseUrl + "/items/all";
        Iterable<RestTemplate> r = restTemplate.getForObject(url, Iterable.class);
        model.addAttribute("allItems", r);
        model.addAttribute("itemNumber", "item number");
        model.addAttribute("name", "Name");
        model.addAttribute("itemTitle", "Items in stock");
        return "items";
    }

}

