package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Demonstrates the 4 CRUD operations:
 * Create (POST), Read (GET), Update (PUT), Delete (DELETE)
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    // READ All
    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    // CREATE
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee updatedDetails) {
        return repository.findById(id).map(emp -> {
            emp.setName(updatedDetails.getName());
            emp.setDepartment(updatedDetails.getDepartment());
            return repository.save(emp);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "Deleted successfully!";
    }
}
