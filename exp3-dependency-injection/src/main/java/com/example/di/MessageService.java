package com.example.di;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public String getMessage() {
        return "Message from injected MessageService!";
    }
}
