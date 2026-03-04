package com.example.Calendar.service;

import com.example.Calendar.model.entities.User;
import com.example.Calendar.model.dto.request.SignUpRequest;
import com.example.Calendar.model.enums.Gender;
import com.example.Calendar.model.enums.Role;
import com.example.Calendar.repository.UserRepository;
import com.example.Calendar.service.intefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(SignUpRequest signUpRequest, String encodedPassword){
        User user = new User();
            user.setUuid(UUID.randomUUID());
            user.setUsername(signUpRequest.getUsername());
            user.setNormalizedUsername(signUpRequest.getUsername().toUpperCase());
            user.setPassword(encodedPassword);
            user.setGender(Gender.OTHER);
            user.setRole(Role.USER);
            user.setActive(true);
            user.setCreateDate(LocalDateTime.now());

        return user;
    }

    public void deleteUser(){

    }

    public User getUser(String username){
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
        return user;
    }
}
