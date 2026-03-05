package com.example.Calendar.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/model")
@CrossOrigin(origins = "http://localhost:5173")
public class AgentController {



    @GetMapping("/answer")
    public String generateAnswer(@RequestParam String message){ // Flux<String>
        System.out.println(message);
        return message;
    }
}
