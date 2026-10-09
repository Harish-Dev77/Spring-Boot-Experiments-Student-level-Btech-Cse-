package com.example.unittesting.repository;

import com.example.unittesting.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(Long id);
    User save(User user);
}
