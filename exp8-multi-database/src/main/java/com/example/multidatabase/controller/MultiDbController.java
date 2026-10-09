package com.example.multidatabase.controller;

import com.example.multidatabase.model.primary.PrimaryUser;
import com.example.multidatabase.model.secondary.SecondaryOrder;
import com.example.multidatabase.repository.primary.PrimaryUserRepository;
import com.example.multidatabase.repository.secondary.SecondaryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MultiDbController {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;

    @Autowired
    private SecondaryOrderRepository secondaryOrderRepository;

    @GetMapping("/test-multi-db")
    public Map<String, Object> testMultiDb() {
        PrimaryUser user = new PrimaryUser("Alice - DB1");
        primaryUserRepository.save(user);

        SecondaryOrder order = new SecondaryOrder("Laptop - DB2");
        secondaryOrderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("PrimaryDbUsers", primaryUserRepository.findAll());
        response.put("SecondaryDbOrders", secondaryOrderRepository.findAll());

        return response;
    }
}
