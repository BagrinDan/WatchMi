package com.example.Calendar.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Email(message = "Invalid email address")
    @NotBlank(message = "Login cannot be empty")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 100)
    private String confirmPassword;
}
