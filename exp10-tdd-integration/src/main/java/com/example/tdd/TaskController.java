package com.example.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * TDD STEP 2 (GREEN): Creating the Controller
 * This handles the HTTP POST request to /api/tasks that the test is sending.
 * Without this, the test fails with a 404 Not Found error.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // This method exactly matches what the TDD test is expecting:
    // 1. POST request to /api/tasks
    // 2. Returns 201 Created
    // 3. Returns the saved JSON object
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Save to in-memory H2 database
        Task savedTask = taskRepository.save(task);
        
        // Return 201 Created status and the saved task (which now has an ID)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }
}
