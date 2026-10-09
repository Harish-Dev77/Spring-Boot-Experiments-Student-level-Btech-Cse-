package com.example.exceptionhandling.controller;

import com.example.exceptionhandling.exception.UserNotFoundException;
import com.example.exceptionhandling.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = Arrays.asList(
            new User(1L, "Alice"),
            new User(2L, "Bob")
    );

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
    
    @GetMapping("/error")
    public User triggerGeneralError() {
        throw new RuntimeException("This is a simulated internal server error");
    }
}
