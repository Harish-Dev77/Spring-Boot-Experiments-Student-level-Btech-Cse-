package com.example.unittesting.controller;

import com.example.unittesting.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetGreeting_Success() throws Exception {
        // Arrange
        when(userService.getUserGreeting(1L)).thenReturn("Hello, Alice!");

        // Act & Assert
        mockMvc.perform(get("/api/users/1/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Alice!"));
    }

    @Test
    public void testGetGreeting_NotFound() throws Exception {
        // Arrange
        when(userService.getUserGreeting(2L)).thenReturn("User not found");

        // Act & Assert
        mockMvc.perform(get("/api/users/2/greeting"))
                .andExpect(status().isNotFound());
    }
}
