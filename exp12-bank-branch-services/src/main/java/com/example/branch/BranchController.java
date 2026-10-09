package com.example.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchRepository repository;

    @GetMapping
    public List<Branch> getAllBranches() {
        return repository.findAll();
    }

    @PostMapping
    public Branch addBranch(@RequestBody Branch branch) {
        return repository.save(branch);
    }
}
