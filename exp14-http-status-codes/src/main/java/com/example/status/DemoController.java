package com.example.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller to trigger different exceptions based on user input.
 * E.g. /api/demo?type=notfound
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping
    public String testException(@RequestParam(required = false) String type) {
        if (type == null) {
            return "Provide a type parameter: ?type=notfound, ?type=badrequest, ?type=conflict, or ?type=error";
        }
        
        switch (type.toLowerCase()) {
            case "notfound":
                throw new ResourceNotFoundException("The requested resource was not found in the system.");
            case "badrequest":
                throw new BadRequestException("The provided data is invalid or missing required fields.");
            case "conflict":
                throw new ConflictException("A resource with the same identifier already exists.");
            case "error":
                throw new RuntimeException("Simulating a massive database failure!");
            default:
                return "Type not recognized. Try notfound, badrequest, conflict, or error.";
        }
    }
}
