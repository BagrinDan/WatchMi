package com.example.Calendar.service;

import com.example.Calendar.service.intefaces.ModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ModelServiceImpl implements ModelService {

    private final RestTemplate restTemplate = new RestTemplate();



    @Value("${spring.ai.ollama.chat.options.model}")
    String model;

    public String generateAnswer(String promnt){
        String url = "http://localhost:11434/api/generate";

        Map<String, Object> request = Map.of(
                "model", model,
                "prompt", promnt,
                "stream", false
        );

        Map response = restTemplate.postForObject(url, request, Map.class);

        return (String) response.get("response");
    }
}
