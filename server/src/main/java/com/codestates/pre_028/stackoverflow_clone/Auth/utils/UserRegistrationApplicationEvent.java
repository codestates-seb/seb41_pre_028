package com.codestates.pre_028.stackoverflow_clone.Auth.utils;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import lombok.Getter;

@Getter
public class UserRegistrationApplicationEvent {
    private User user;
    public UserRegistrationApplicationEvent(User user) {
        this.user = user;
    }
}
