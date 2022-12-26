package com.codestates.pre_028.stackoverflow_clone.Auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auths")
public class AuthController {
    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String login() {
        System.out.println("Login successfully!");
        return "home";
    }
}
