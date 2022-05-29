package com.example.frontendprojektarbetebackend;

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

    List itemList = new ArrayList();

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON() {
        String url = "http://localhost:8080/items/all";
        return this.restTemplate.getForObject(url, String.class);

    }


    @RequestMapping("/all")
    public String getAll(Model model){
        String url = "http://localhost:8080/items/all";
        Iterable<RestTemplate> r = restTemplate.getForObject(url, Iterable.class);
        model.addAttribute("allItems", r);
        model.addAttribute("itemNumber", "item number");
        model.addAttribute("name", "Name");
        model.addAttribute("itemTitle", "Items in stock");
        return "items";
    }

    public Post[] getPostsAsObject() {
        String url = "http://localhost:8080/items/all";
        return this.restTemplate.getForObject(url, Post[].class);
    }

   @RequestMapping("/hej")
    public List getall() {

        String url = "http://localhost:8080/items/all";
        itemList.add(restTemplate.getForObject(url, String.class));
        for (int i = 0; i< itemList.size(); i++){
            System.out.println(itemList.get(i));
        }

        return itemList;
    }



}

