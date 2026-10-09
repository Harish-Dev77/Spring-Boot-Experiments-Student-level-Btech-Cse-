package com.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final List<String> items = new ArrayList<>();

    public ItemController() {
        items.add("Laptop");
        items.add("Mouse");
    }

    @GetMapping
    public List<String> getAllItems() {
        return items;
    }

    @PostMapping
    public String addItem(@RequestBody String newItem) {
        items.add(newItem);
        return newItem + " added successfully!";
    }
}
