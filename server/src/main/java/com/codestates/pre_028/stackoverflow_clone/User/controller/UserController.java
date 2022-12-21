package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity getUser(@PathVariable long id){
        User user = userService.findUser(id);
        UserDto.Response response = mapper.userToUserResponse(user);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    // 유저 리스트 조회 구현 논의
    /*@GetMapping
    public ResponseEntity getUsers(){
        List<User> users = userService.findUsers().stream()
    }*/

    //회원 가입
    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post requestBody){

        User user =mapper.userPostToUser(requestBody);
        User createdUser = userService.createUser(user);
        UserDto.Response response = mapper.userToUserResponse(createdUser);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED);

    }

    @PatchMapping("/{user-id}")
    public ResponseEntity pathUser(@RequestBody UserDto.Patch requestBody,
                                   @PathVariable("user-id") long id){
        requestBody.setUserId(id);

        User user = userService.updateUser(mapper.userPatchToUser(requestBody),id);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.userToUserResponse(user)),HttpStatus.OK);

    }

    @DeleteMapping("/{user-id}")
    public  ResponseEntity deleteUser(@PathVariable("user-id") @Positive long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
