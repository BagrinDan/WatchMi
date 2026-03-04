package com.example.Calendar.service;


import com.example.Calendar.model.entities.User;
import com.example.Calendar.model.dto.request.SignInRequest;
import com.example.Calendar.model.dto.request.SignUpRequest;
import com.example.Calendar.model.dto.response.JwtResponse;
import com.example.Calendar.repository.UserRepository;
import com.example.Calendar.security.JwtCore;
import com.example.Calendar.service.intefaces.AuthService;
import com.example.Calendar.service.intefaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService { //implements AuthService{
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtCore jwtCore;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> RegisterUser(SignUpRequest signUpRequest){
        if(userRepository.existsUserByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[*] Error: Choose different name");
        }

        if(!isValid(signUpRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[*] Error: Password is weak");
        }

        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[*] Error: Passwords doesn't match");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = userService.createUser(signUpRequest, encodedPassword);

        userRepository.save(user);
        return ResponseEntity.ok("[*] Success");
    }

    public ResponseEntity<?> AuthUser(SignInRequest sighInRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            sighInRequest.getUsername(),
                            sighInRequest.getPassword()
                    )
            );

            String jwtToken = jwtCore.generateToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwtToken));
        } catch (BadCredentialsException e ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[*] Invalid username or password");
        }
    }

    public boolean isValid(String password) {
        if (password == null) return false;

        boolean length = password.length() >= 8;
        boolean upper = password.chars().anyMatch(Character::isUpperCase);
        boolean lower = password.chars().anyMatch(Character::isLowerCase);
        boolean digit = password.chars().anyMatch(Character::isDigit);
        boolean special = password.matches(".*[!@#$%^&*()_+\\-\\[\\]}|;:'\",.<>/?].*");


        return length && upper && lower && digit && special;
    }
}
