package com.codestates.pre_028.stackoverflow_clone.restdocs;

import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.controller.CommentController;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.mapper.CommentMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codestates.pre_028.stackoverflow_clone.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.pre_028.stackoverflow_clone.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentMapper mapper;

//    @MockBean
//    private CommentService commentService;

    @Test
    void postAnswerCommentTest() throws Exception{
        //given
        Long answerId = 1L;
        CommentDto.Post post = new CommentDto.Post();
        post.setUserId(1L);
        post.setAnswerId(answerId);
        post.setContent("stub answerComment content");

        String jsonPost = gson.toJson(post);

        CommentDto.Response answerCommentResponse = new CommentDto.Response();
        answerCommentResponse.setCommentId(1L);
        answerCommentResponse.setAnswerId(answerId);
        answerCommentResponse.setUserId(1L);
        answerCommentResponse.setContent("stub answerComment content");
        answerCommentResponse.setCreatedAt(LocalDateTime.now());
        answerCommentResponse.setModifiedAt(LocalDateTime.now());
        answerCommentResponse.setCreatedBy("stub_answerComment_creator");
        answerCommentResponse.setModifiedBy("stub_answerComment_modifier");


        given(mapper.commentPostDtoToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
//        Comment mockResultComment = new Comment();
//        mockResultComment.setCommentId(1L);
//        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(mockResultComment);

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(answerCommentResponse);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers/{id}/comments",answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPost)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.userId").value(post.getUserId()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document(
                        "post-answerComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("답변 ID"))
                        ),
                        requestFields(
                                Arrays.asList(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 ID"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));
    }

    @Test
    void postQuestionCommentTest() throws Exception{
        //given
        Long questionId = 1L;
        CommentDto.Post post = new CommentDto.Post();
        post.setUserId(1L);
        post.setQuestionId(1L);
        post.setContent("stub questionComment content");

        String jsonPost = gson.toJson(post);

        CommentDto.Response questionCommentResponse = new CommentDto.Response();
        questionCommentResponse.setCommentId(1L);
        questionCommentResponse.setQuestionId(questionId);
        questionCommentResponse.setUserId(1L);
        questionCommentResponse.setContent("stub questionComment content");
        questionCommentResponse.setCreatedAt(LocalDateTime.now());
        questionCommentResponse.setModifiedAt(LocalDateTime.now());
        questionCommentResponse.setCreatedBy("stub_questionComment_creator");
        questionCommentResponse.setModifiedBy("stub_questionComment_modifier");

        given(mapper.commentPostDtoToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
//        Comment mockResultComment = new Comment();
//        mockResultComment.setCommentId(1L);
//        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(mockResultComment);

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(questionCommentResponse);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/{id}/comments",questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPost)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.userId").value(post.getUserId()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document(
                        "post-questionComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("질문 ID"))
                        ),
                        requestFields(
                                Arrays.asList(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));

    }

    @Test
    void patchAnswerCommentTest() throws Exception{
        //given
        Long answerId = 1L;
        Long commentId = 1L;
        CommentDto.Patch patch = new CommentDto.Patch();
        patch.setCommentId(1L);
        patch.setContent("stub answerComment content");

        String jsonPatch = gson.toJson(patch);

        CommentDto.Response answerCommentResponse = new CommentDto.Response();
        answerCommentResponse.setCommentId(1L);
        answerCommentResponse.setAnswerId(answerId);
        answerCommentResponse.setUserId(1L);
        answerCommentResponse.setContent("stub answerComment content");
        answerCommentResponse.setCreatedAt(LocalDateTime.now());
        answerCommentResponse.setModifiedAt(LocalDateTime.now());
        answerCommentResponse.setCreatedBy("stub_answerComment_creator");
        answerCommentResponse.setModifiedBy("stub_answerComment_modifier");

        given(mapper.commentPatchDtoToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());
//        Comment mockResultComment = new Comment();
//        mockResultComment.setCommentId(1L);
//        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(mockResultComment);

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(answerCommentResponse);

        //when
        ResultActions actions =
                mockMvc.perform(
                        patch("/answers/{id}/comments/{comment-id}",answerId,commentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPatch)
                );


        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentId").value(patch.getCommentId()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document(
                        "patch-answerComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("답변 ID"),
                                        parameterWithName("comment-id").description("댓글 ID"))

                        ),
                        requestFields(
                                Arrays.asList(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));

    }

    @Test
    void patchQuestionCommentTest() throws Exception{
        Long questionId = 1L;
        Long commentId = 1L;
        CommentDto.Patch patch = new CommentDto.Patch();
        patch.setCommentId(1L);
        patch.setContent("stub questionComment content");

        String jsonPatch = gson.toJson(patch);

        CommentDto.Response questionCommentResponse = new CommentDto.Response();
        questionCommentResponse.setCommentId(1L);
        questionCommentResponse.setQuestionId(questionId);
        questionCommentResponse.setUserId(1L);
        questionCommentResponse.setContent("stub questionComment content");
        questionCommentResponse.setCreatedAt(LocalDateTime.now());
        questionCommentResponse.setModifiedAt(LocalDateTime.now());
        questionCommentResponse.setCreatedBy("stub_questionComment_creator");
        questionCommentResponse.setModifiedBy("stub_questionComment_modifier");


        given(mapper.commentPatchDtoToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());
//        Comment mockResultComment = new Comment();
//        mockResultComment.setCommentId(1L);
//        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(mockResultComment);

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(questionCommentResponse);

        //when
        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/{id}/comments/{comment-id}",questionId,commentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPatch)
                );


        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentId").value(patch.getCommentId()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document(
                        "patch-questionComment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("질문 ID"),
                                        parameterWithName("comment-id").description("댓글 ID"))

                        ),
                        requestFields(
                                Arrays.asList(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));
    }

    @Test
    void getAnswerCommentsTest() throws Exception{
        Long answerId = 1L;

        CommentDto.Response response1 = new CommentDto.Response();
        response1.setCommentId(1L);
        response1.setAnswerId(answerId);
        response1.setUserId(1L);
        response1.setContent("stub answerComment content");
        response1.setCreatedAt(LocalDateTime.now());
        response1.setModifiedAt(LocalDateTime.now());
        response1.setCreatedBy("stub_answerComment_creator");
        response1.setModifiedBy("stub_answerComment_modifier");

        CommentDto.Response response2 = new CommentDto.Response();
        response2.setCommentId(2L);
        response2.setAnswerId(2L);
        response2.setUserId(2L);
        response2.setContent("stub answerComment content2");
        response2.setCreatedAt(LocalDateTime.now());
        response2.setModifiedAt(LocalDateTime.now());
        response2.setCreatedBy("stub_answerComment_creator2");
        response2.setModifiedBy("stub_answerComment_modifier2");


        given(mapper.commentsToCommentResponseDtos(Mockito.any(List.class))).willReturn(List.of(response1,response2));

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/answers/{id}/comments",answerId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-answerComments",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("답변 ID"))
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                        fieldWithPath("data[].commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data[].userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data[].createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data[].modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));
    }

    @Test
    void getQuestionCommentsTest() throws Exception{
        Long questionId = 1L;
        CommentDto.Response response1 = new CommentDto.Response();
        response1.setCommentId(1L);
        response1.setQuestionId(questionId);
        response1.setUserId(1L);
        response1.setContent("stub questionComment content");
        response1.setCreatedAt(LocalDateTime.now());
        response1.setModifiedAt(LocalDateTime.now());
        response1.setCreatedBy("stub_questionComment_creator");
        response1.setModifiedBy("stub_questionComment_modifier");

        CommentDto.Response response2 = new CommentDto.Response();
        response2.setCommentId(2L);
        response2.setQuestionId(2L);
        response2.setUserId(2L);
        response2.setContent("stub questionComment content");
        response2.setCreatedAt(LocalDateTime.now());
        response2.setModifiedAt(LocalDateTime.now());
        response2.setCreatedBy("stub_questionComment_creator2");
        response2.setModifiedBy("stub_questionComment_modifier2");

        given(mapper.commentsToCommentResponseDtos(Mockito.any(List.class))).willReturn(List.of(response1,response2));

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/{id}/comments",questionId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-questionComments",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("답변 ID"))
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                        fieldWithPath("data[].commentId").type(JsonFieldType.NUMBER).description("댓글 ID"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 ID").optional(),
                                        fieldWithPath("data[].userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 ID").optional(),
                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("댓글 생성일"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정일"),
                                        fieldWithPath("data[].createdBy").type(JsonFieldType.STRING).description("댓글 생성자"),
                                        fieldWithPath("data[].modifiedBy").type(JsonFieldType.STRING).description("댓글 수정자")
                                )
                        )
                ));
    }

    @Test
    void deleteAnswerCommentTest() throws Exception{
        //given
        Long answerId = 1L;
        Long commentId = 1L;
        //doNothing().when(commentService).deleteComment(Mockito.anyLong());

        //when
        ResultActions actions = mockMvc
                .perform(
                        delete("/answers/{id}/comments/{comment-id}",answerId,commentId));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answerComment",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("id").description("답변 ID"),
                                                parameterWithName("comment-id").description("댓글 ID"))
                                )
                        )
                );
    }

    @Test
    void deleteQuestionCommentTest() throws Exception{
        //given
        Long questionId = 1L;
        Long commentId = 1L;
        //doNothing().when(commentService).deleteComment(Mockito.anyLong());

        //when
        ResultActions actions = mockMvc
                .perform(
                        delete("/questions/{id}/comments/{comment-id}",questionId,commentId));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-questionComment",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("id").description("질문 ID"),
                                                parameterWithName("comment-id").description("댓글 ID"))
                                )
                        )
                );
    }

}
