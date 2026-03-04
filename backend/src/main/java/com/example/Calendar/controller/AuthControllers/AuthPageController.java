package com.example.Calendar.controller.AuthControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthPageController {
    // Get methods
    @GetMapping("/login")
    public String loginPage(){
        return "public/login/login"; // Лучше сделать auth/login
    }

    @GetMapping("/register")
    public String registerPage(){
        return "public/login/register"; // и auth/register соответсвенно
    }
}
