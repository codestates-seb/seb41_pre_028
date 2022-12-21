package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(UserControllerTest.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserControllerTest {

    @Test
    void getUser() {
        // Given
        user.setNickname("test_ing");

        // When



        // Then
    }

    @Test
    void getUsers() {
    }

    @Test
    void postUser() {
    }

    @Test
    void pathUser() {
    }

    @Test
    void deleteUser() {
    }


    User user = new User(1L, "asdf1234", "test@gmail.com", "test_nickname", 0);
}