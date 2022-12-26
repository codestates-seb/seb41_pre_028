package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.Auth.Dto.LoginDto;
import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.assembler.UserAssembler;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;
    private final UserAssembler userAssembler;
    private final UserMapper mapper;

    public UserController(UserService userService, UserAssembler userAssembler, UserMapper mapper) {
        this.userService = userService;
        this.userAssembler = userAssembler;
        this.mapper = mapper;
    }

    // 유저 단건 조회
    @GetMapping ("/{id}")
    public EntityModel<User> getUser(@PathVariable long id){
        User user = userService.findUser(id);


        return userAssembler.toModel(user);
    }

    //JWT 토큰만 사용하여 유저 정보 Return
    @GetMapping("/mypage")
    public ResponseEntity getUserFromToken(HttpServletRequest request){
        String email = (String)request.getAttribute("email");
        User user = userService.getLoginUserWithToken();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> users = userService.findUsers().stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserService.class).findUsers()).withSelfRel());
    }
    //회원 가입

    @PostMapping("/signup")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDto.Post requestBody){

        User user =mapper.userPostToUser(requestBody);
        EntityModel<User> entityModel = userAssembler.toModel(userService.createUser(user));

        return  ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

//    @PostMapping("/login")
//    public ResponseEntity postLoginUser(@RequestBody LoginDto requestBody , HttpServletResponse response){
//        User user = mapper.loginUserPostToUser(requestBody);
//
//
//        return new ResponseEntity<>(user,  HttpStatus.OK);
//    }

    @PatchMapping("/{user-id}")
    public ResponseEntity pathUser(@RequestBody User requestBody,
                                   @PathVariable("user-id") long id){
        EntityModel<User> entityModel = userAssembler.toModel(userService.updateUser(requestBody, id));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @DeleteMapping("/{user-id}")
    public  ResponseEntity deleteUser(@PathVariable("user-id") @Positive long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
