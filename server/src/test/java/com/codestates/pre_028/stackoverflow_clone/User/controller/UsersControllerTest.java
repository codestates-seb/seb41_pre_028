package com.codestates.pre_028.stackoverflow_clone.User.controller;

import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codestates.pre_028.stackoverflow_clone.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.pre_028.stackoverflow_clone.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled
@DisplayName("Data REST - API 테스트")
@Transactional
@WebMvcTest(UsersControllerTest.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UsersControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private MockMvc mvc;
    @MockBean

    private UserMapper mapper;

    @DisplayName("회원 정보 조회")
    @Test
    void getUser() throws Exception {
        // Given
        User user = new User("asdf1234","test@email.com","test_nickname1",0);
        user.setUserId(1L);
        UserDto.Response responseDto =
                new UserDto.Response(
                        1L,
                        "tet_nickname1",
                        "test@email.com",
                        0
                );

        given(userService.findUser(anyLong())).willReturn(new User());
        given(mapper.userToUserResponse(any(User.class))).willReturn(responseDto);

        // When
        ResultActions actions =
                mvc.perform(
                        get("/users/{user-id}", user.getUserId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)


                );
        // Then
        actions.andExpect(status().isOk())
                .andDo(document("/get-user",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("user-Id").description("유저 ID")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평판")
                                )
                        )));
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