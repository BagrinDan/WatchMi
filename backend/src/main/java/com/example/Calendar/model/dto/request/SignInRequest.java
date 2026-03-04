package com.example.Calendar.model.dto.request;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 100)
    private String password;
}
