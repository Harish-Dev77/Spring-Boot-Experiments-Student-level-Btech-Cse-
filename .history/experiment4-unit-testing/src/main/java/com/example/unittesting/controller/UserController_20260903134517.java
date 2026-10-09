package com.example.unittesting.controller;

import com.example.unittesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {  private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/greeting")
    public ResponseEntity<String> getGreeting(@PathVariable Long id) {
        String greeting = userService.getUserGreeting(id);
        if ("User not found".equals(greeting)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(greeting);
    }
}
