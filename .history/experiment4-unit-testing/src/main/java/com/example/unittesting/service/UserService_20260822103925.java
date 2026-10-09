package com.example.unittesting.service;

import com.example.unittesting.model.User;
import com.example.unittesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserGreeting(Long id) {
        return userRepository.findById(id)
                .map(user -> "Hello, " + user.getName() + "!")
                .orElse("User not found");
    }
}
