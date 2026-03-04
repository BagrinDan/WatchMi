package com.example.Calendar.service.intefaces;

import com.example.Calendar.model.dto.request.SignInRequest;
import com.example.Calendar.model.dto.request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {
    ResponseEntity<?> RegisterUser(SignUpRequest signUpRequest);
    ResponseEntity<?> AuthUser(SignInRequest sighInRequest);
}
