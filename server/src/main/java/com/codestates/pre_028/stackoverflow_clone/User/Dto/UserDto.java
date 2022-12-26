package com.codestates.pre_028.stackoverflow_clone.User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Post{

        @NotBlank(message = "email 은 공백이 아니어야 합니다")
        @Email
        private String email;

        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String nickname;

        @NotBlank
        private String password;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long userId;
        @Email
        private String email;

        private String nickname;
        public void setUserId(long userId){this.userId = userId;}
    }


    @AllArgsConstructor
    @Getter
    public static class Response{
        private Long userId;
        private String nickname;
        private String email;
        private Integer reputation;
    }





}
