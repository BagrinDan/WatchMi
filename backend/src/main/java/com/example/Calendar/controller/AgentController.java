package com.example.Calendar.controller;


import com.example.Calendar.service.intefaces.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/model")
@CrossOrigin(origins = "http://localhost:5173")
public class AgentController {

    private final ModelService modelService;

    @GetMapping("/answer")
    public String generateAnswer(@RequestParam String message){ // Flux<String>
        String response = modelService.generateAnswer(message);
        System.out.println(response);
        return response;
    }
}
