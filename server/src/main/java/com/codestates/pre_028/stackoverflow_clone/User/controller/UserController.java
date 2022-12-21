package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

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

    //회원 가입
    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post requestBody){

        User user =mapper.userPostToUser(requestBody);
        User createdUser = userService.createUser(user);
        UserDto.Response response = mapper.userToUserResponse(createdUser);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED);

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
