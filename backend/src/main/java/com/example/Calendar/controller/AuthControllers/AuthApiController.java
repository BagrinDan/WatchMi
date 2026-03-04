package com.example.Calendar.controller.AuthControllers;

import com.example.Calendar.model.dto.request.SignInRequest;
import com.example.Calendar.model.dto.request.SignUpRequest;
import com.example.Calendar.service.AuthServiceImpl;
import com.example.Calendar.service.intefaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthApiController {
    private final AuthService authService;

    @Autowired
    public AuthApiController(AuthServiceImpl authService){
        this.authService = authService;
    }

    // Post methods
    @PostMapping("/register")
    public ResponseEntity<?> createUserAccount(@Valid @RequestBody SignUpRequest signUpRequest){ // RequestBody = Swagger, ModelAttribute = HTML
        return authService.RegisterUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authUserAccount(@Valid @RequestBody SignInRequest signInRequest){
        System.out.println("[*] Login attempt: " + signInRequest.getUsername());
        return authService.AuthUser(signInRequest);
    }
}
