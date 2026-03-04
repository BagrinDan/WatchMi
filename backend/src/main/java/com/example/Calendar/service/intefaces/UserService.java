package com.example.Calendar.service.intefaces;

import com.example.Calendar.model.entities.User;
import com.example.Calendar.model.dto.request.SignUpRequest;

public interface UserService {
    User createUser(SignUpRequest signUpRequest, String encodedPassword);
    User getUser(String username);
}

