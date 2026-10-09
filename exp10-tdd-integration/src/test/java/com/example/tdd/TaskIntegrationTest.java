package com.example.tdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * EXPERIMENT 6: Integration Test with TDD (Test-Driven Development)
 * 
 * TDD has 3 steps:
 * 1. RED: Write the test first. It will fail because the code doesn't exist yet.
 * 2. GREEN: Write the minimum code needed to make the test pass.
 * 3. REFACTOR: Improve the code while keeping the test green.
 * 
 * This is an Integration Test, meaning it tests the entire flow:
 * HTTP Request -> Controller -> Service -> Database.
 */
@SpringBootTest
@AutoConfigureMockMvc // Allows us to use MockMvc to simulate HTTP requests
class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Used to convert Java objects to JSON

    @Test
    void shouldCreateNewTask_TDD() throws Exception {
        // TDD STEP 1 (RED): We write this test assuming Task and /api/tasks exist.
        // If we run this before creating TaskController, it will fail (404 Not Found).
        
        // 1. Arrange: Create the JSON request body
        String requestBody = "{\"title\": \"Learn TDD\", \"completed\": false}";

        // 2. Act: Send POST request to /api/tasks
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                
        // 3. Assert: Verify the response is 201 Created and JSON contains the right data
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists()) // DB should generate an ID
                .andExpect(jsonPath("$.title").value("Learn TDD"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}
