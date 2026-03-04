package com.example.Calendar.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.List;

@Data
public class FilmRequest {
    @NotBlank(message = "Title can't be empty")
    @Size(min = 3, max = 50)
    private String title;

    @Size(min = 3, max = 100)
    private String description;

    @NotBlank(message = "Genre can't be empty")
    @Size(min = 3, max = 10)
    private String genre;

    //private LocalTime date = LocalTime.now(); // TODO: Decide -> BD or DTO?

    private List<String> withWho; //TODO: 1. Option "Alone" by default, 2. Constrains
}
