package com.example.Calendar.model.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class FilmResponse {
    private String title;
    private String description;
    private LocalTime date;
    private List<String> withWho;
}
