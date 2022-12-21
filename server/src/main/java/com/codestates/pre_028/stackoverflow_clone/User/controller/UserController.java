package com.codestates.pre_028.stackoverflow_clone.User.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    // 유저 단건 조회
    @GetMapping ("/{id}")
    public ResponseEntity getUser(){
        return null;
    }

    // 유저 리스트 조회
    @GetMapping
    public ResponseEntity getUsers(){
        return null;
    }

    @PostMapping
    public ResponseEntity postUser(){
        return null;
    }

    @PatchMapping
    public ResponseEntity pathUser(){
        return null;
    }

    @DeleteMapping
    public  ResponseEntity deleteUser(){
        return null;
    }
}
