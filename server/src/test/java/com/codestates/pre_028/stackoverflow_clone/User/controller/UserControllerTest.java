package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerTest.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper mapper;
    @Autowired
    private Gson gson;


    @Test
    void getUser() throws Exception {
        // Given
        User user = new User("asdf1234","test@email.com","test_nickname1",0);
        user.setUserId(1L);

        UserDto.Response response =
                new UserDto.Response(1L,
                        "test_nickname1",
                        "test@email.com"
                        , 0);

        given(mapper.userToUserResponse(Mockito.any(User.class))).willReturn(response);
        // When
        ResultActions actions =
                mvc.perform(
                        RestDocumentationRequestBuilders.get("/users/{user-id}" , user.getUserId())
                                .accept(MediaType.APPLICATION_JSON)

                );
        // Then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.data.email").value(user.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(user.getNickname()))
                .andExpect(jsonPath("$.data.reputation").value(user.getReputation()));
//                .andDo(document("get-user"),
//                        pathParameters(
//                                parameterWithName("user-id").description("회원 식별자")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평판")
//                                )
//                        ))
    }

    @Test
    void getUsers() {
    }

    @Test
    void postUser() {
        // Given
        // When

        // Then
    }

    @Test
    void pathUser() {
    }

    @Test
    void deleteUser() {
    }


}