package com.codestates.pre_028.stackoverflow_clone.config;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
    @MockBean
    private UserRepository userRepository;

    @BeforeTestMethod
    public void sercuritySetup(){
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(new User(
                "asdf1234",
                "test@email.com",
                "test1",
                0
                )
        ));
    }
}
