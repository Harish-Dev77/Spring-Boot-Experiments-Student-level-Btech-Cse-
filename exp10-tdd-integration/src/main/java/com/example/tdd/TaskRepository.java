package com.example.tdd;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * TDD STEP 2 (GREEN): Creating the Repository
 * This provides the database operations needed to save the Task.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
