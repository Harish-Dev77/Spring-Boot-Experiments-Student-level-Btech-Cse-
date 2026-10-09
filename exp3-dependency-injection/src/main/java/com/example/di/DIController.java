package com.example.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DIController {

    private final MessageService constructorInjectedService;
    private MessageService setterInjectedService;

    // 1. Constructor Injection (Recommended)
    @Autowired
    public DIController(MessageService constructorInjectedService) {
        this.constructorInjectedService = constructorInjectedService;
    }

    // 2. Setter Injection
    @Autowired
    public void setSetterInjectedService(MessageService setterInjectedService) {
        this.setterInjectedService = setterInjectedService;
    }

    @GetMapping("/di/constructor")
    public String getConstructorMessage() {
        return "Constructor Injection: " + constructorInjectedService.getMessage();
    }

    @GetMapping("/di/setter")
    public String getSetterMessage() {
        return "Setter Injection: " + setterInjectedService.getMessage();
    }
}
