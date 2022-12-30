//package com.codestates.pre_028.stackoverflow_clone.User.controller;
//
//import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
//import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.User.mapper.UserMapper;
//import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static com.codestates.pre_028.stackoverflow_clone.utils.ApiDocumentUtils.getRequestPreProcessor;
//import static com.codestates.pre_028.stackoverflow_clone.utils.ApiDocumentUtils.getResponsePreProcessor;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@DisplayName("Data REST - API 테스트")
//@WebMvcTest(value = UserController.class, excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)  //REST 테스트를 위한 시큐리티 설정 해제
//})
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class UsersControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private Gson gson;
//
//    @MockBean
//    private UserService userService;
//    @MockBean
//
//    private UserMapper mapper;
//
//
//    @WithMockUser()
//    @DisplayName("회원 정보 조회")
//    @Test
//    void getUser() throws Exception {
//        // Given
//        User user = new User("asdf1234","test@email.com","test_nickname1",0);
//        user.setUserId(1L);
//        UserDto.Response responseDto =
//                new UserDto.Response(
//                        1L,
//                        "test_nickname1",
//                        "test@email.com",
//                        0
//                );
//
//        given(userService.findUser(anyLong())).willReturn(new User());
//        given(mapper.userToUserResponse(any(User.class))).willReturn(responseDto);
//
//        // When
//        ResultActions actions =
//                mvc.perform(
//                        RestDocumentationRequestBuilders.get("/users/{user-id}", user.getUserId())
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//        // Then
//        actions.andExpect(status().isOk())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.userId").value(user.getUserId()))
//                .andExpect(jsonPath("$.data.email").value(user.getEmail()))
//                .andExpect(jsonPath("$.data.nickname").value(user.getNickname()))
//                .andDo(document("/get-user",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
////                        pathParameters(
////                                parameterWithName("user-Id").description("유저 ID").optional()
////                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평판")
//                                )
//                        )));
//    }
//
//    @Test
//    void getUsers() {
//    }
//
//    @WithMockUser
//    @DisplayName("회원 가입")
//    @Test
//    void postUser() throws Exception {
//        // Given
//        UserDto.Post post = new UserDto.Post("test@email.com","test", "asdf4321");
//        String content = gson.toJson(post);
//
//        UserDto.Response responseDto =
//                new UserDto.Response(1L,
//                        "test",
//                        "test@email.com",
//                        0);
//        // When
//
//        given(mapper.userPostToUser(Mockito.any(UserDto.Post.class))).willReturn(new User());
//
//        given(userService.createUser(Mockito.any(User.class))).willReturn(new User());
//
//        given(mapper.userToUserResponse(Mockito.any(User.class))).willReturn(responseDto);
//
//        ResultActions actions =
//                mvc.perform(
//                        RestDocumentationRequestBuilders.post("/users/signup")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                                .with(csrf())
//                );
//        // Then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
//                .andExpect(jsonPath("$.data.nickname").value(post.getNickname()))
////                .andExpect(jsonPath("$.data.password").value(post.getPassword()))  //복호화 된 비밀번호라 매칭 불가
//
//                .andDo(document("post-user",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평판")
//                                )
//                        )
//                ));
//
//    }
//    @DisplayName("회원 정보 수정")
//    @WithMockUser
//    @Test
//    void pathUser() throws Exception {
//        // Given
//        long userId = 1L;
//        UserDto.Patch patch = new UserDto.Patch(userId, "test2@email.com", "test2");
//        String content = gson.toJson(patch);
//
//        UserDto.Response responseDto=
//                new UserDto.Response(
//                        1L,
//                        "test2",
//                        "test2@email.com",
//                        0
//                );
//        // When
//        given(mapper.userPatchToUser(Mockito.any(UserDto.Patch.class))).willReturn(new User());
//
//        given(userService.updateUser(Mockito.any(User.class), anyLong())).willReturn(new User());
//
//        given(mapper.userToUserResponse(Mockito.any(User.class))).willReturn(responseDto);
//
//        ResultActions actions =
//                mvc.perform(
//                        RestDocumentationRequestBuilders.patch("/users/{user-id}", userId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                                .with(csrf())
//                );
//        // Then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.userId").value(patch.getUserId()))
//                .andDo(document("patch-user",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("user-id").description("회원 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평판")
//                                        )
//                        )));
//
//
//    }
//
//    @DisplayName("회원 삭제")
//    @WithMockUser
//    @Test
//    void deleteUser() throws Exception {
//        // Given
//        Long userId = 1L;
//        doNothing().when(userService).deleteUser(Mockito.anyLong());
//
//        // When
//        ResultActions actions =
//                mvc.perform(RestDocumentationRequestBuilders
//                        .delete("/users/{user-id}",userId)
//                        .with(csrf()));
//
//        // Then
//        actions
//                .andExpect(status().isNoContent())
//                .andDo(document("delete-users",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("user-id").description("회원 식별자")
//                        )));
//    }
//
//
//}
