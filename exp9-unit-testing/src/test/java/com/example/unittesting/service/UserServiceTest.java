package com.example.unittesting.service;

import com.example.unittesting.model.User;
import com.example.unittesting.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserGreeting_UserExists() {
        // Arrange
        User mockUser = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Act
        String greeting = userService.getUserGreeting(1L);

        // Assert
        assertEquals("Hello, Alice!", greeting);
    }

    @Test
    public void testGetUserGreeting_UserDoesNotExist() {
        // Arrange
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        String greeting = userService.getUserGreeting(2L);

        // Assert
        assertEquals("User not found", greeting);
    }
}
