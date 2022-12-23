package com.codestates.pre_028.stackoverflow_clone.restdocs;


import com.codestates.pre_028.stackoverflow_clone.answer.controller.AnswerController;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.mapper.AnswerMapper;
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
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerMapper mapper;

    @Test
    void postAnswerTest() throws Exception{
        //given
        AnswerDto.Post post = new AnswerDto.Post(1,1,"stub data");
        String jsonPost = gson.toJson(post);

        AnswerDto.Response response = new AnswerDto.Response(1,1,1,
                "stub data",
                Answer.AnswerStatus.ANSWER_NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "stubUser",
                "stubUser");

        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());

//        Answer mockResultAnswer = new Answer();
//        mockResultAnswer.setAnswerId(1L);
//        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);
        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPost)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.userId").value(response.getUserId()))
                .andExpect(jsonPath("$.data.questionId").value(response.getQuestionId()))
                .andExpect(jsonPath("$.data.content").value(response.getContent()))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.answerStatus").type(JsonFieldType.STRING).description("답변 상태: 일반 답변 / 채택된 답변 / 삭제된 답변"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("답변 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("답변 수정자")
                                )
                        )
                ));
    }

    @Test
    void patchAnswerTest() throws Exception{
        //given
        long answerId = 1L;
        AnswerDto.Patch patch = new AnswerDto.Patch(answerId,
                Answer.AnswerStatus.ANSWER_NORMAL,
                "stub data");
        String jsonPatch = gson.toJson(patch);

        AnswerDto.Response response = new AnswerDto.Response(1,1,1,
                "stub data",
                Answer.AnswerStatus.ANSWER_NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "stubUser",
                "stubUser");

        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());

//        Answer mockResultAnswer = new Answer();
//        mockResultAnswer.setAnswerId(1L);
//        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        patch("/answers/{id}",answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPatch)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.data.answerStatus").value(patch.getAnswerStatus().getStatus()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document("patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                Arrays.asList(parameterWithName("id").description("답변 ID"))
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 ID").ignored(),
                                        fieldWithPath("answerStatus").type(JsonFieldType.STRING).description("답변 상태: ANSWER_NORMAL / ANSWER_ADOPTED / ANSWER_DELETED").optional(),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("본문").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                        fieldWithPath("data.answerStatus").type(JsonFieldType.STRING).description("답변 상태: 일반 답변 / 채택된 답변 / 삭제된 답변"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성일"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정일"),
                                        fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("답변 생성자"),
                                        fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("답변 수정자")
                                )
                        )
                ));
    }

    @Test
    void getAnswerTest() throws Exception{
        //given
        Long answerId = 1L;
        AnswerDto.Response response = new AnswerDto.Response(1,1,1,
                "stub data",
                Answer.AnswerStatus.ANSWER_NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "stubUser",
                "stubUser");


        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        //given(answerService.findAnswer(Mockito.any(Long.class))).willReturn(new Answer());

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/answers/{id}", answerId)
                                .accept(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(response.getAnswerId()))
                .andExpect(jsonPath("$.data.content").value(response.getContent()))
                .andDo(
                        document("get-answer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("id").description("답변 ID"))
                                ),
                                responseFields(
                                       Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 ID"),
                                                fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                                fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("본문"),
                                                fieldWithPath("data.answerStatus").type(JsonFieldType.STRING).description("답변 상태: 일반 답변 / 채택된 답변 / 삭제된 답변"),
                                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성일"),
                                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정일"),
                                                fieldWithPath("data.createdBy").type(JsonFieldType.STRING).description("답변 생성자"),
                                                fieldWithPath("data.modifiedBy").type(JsonFieldType.STRING).description("답변 수정자")
                                        )
                                )
                        ));
    }

    @Test
    void getAnswersTest() throws Exception{
        //given
        AnswerDto.Response response1 = new AnswerDto.Response(1,1,1,
                "stub data1",
                Answer.AnswerStatus.ANSWER_NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "stubUser1",
                "stubUser1");
        AnswerDto.Response response2 = new AnswerDto.Response(2,2,2,
                "stub data2",
                Answer.AnswerStatus.ANSWER_NORMAL,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "stubUser2",
                "stubUser2");

        given(mapper.answersToAnswerResponseDtos(Mockito.any(List.class))).willReturn(new ArrayList());

        //given(answerService.findAnswers(Mockito.any(Long.class))).willReturn(new Answer());

        //when
        ResultActions actions = mockMvc.perform(
                get("/answers")
                        .accept(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "get-answers",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                              //requestParameters(
                                //                                    List.of(
                                //                                            parameterWithName("page").description("Page 번호"),
                                //                                            parameterWithName("size").description("Page Size")
                                //                                    )
                                //                            ),
                                responseFields(
                                        Arrays.asList(
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                                fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 ID"),
                                                fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 ID"),
                                                fieldWithPath("data[].userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                                fieldWithPath("data[].content").type(JsonFieldType.STRING).description("본문"),
                                                fieldWithPath("data[].answerStatus").type(JsonFieldType.STRING).description("답변 상태: 일반 답변 / 채택된 답변 / 삭제된 답변"),
                                                fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("답변 생성일"),
                                                fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정일"),
                                                fieldWithPath("data[].createdBy").type(JsonFieldType.STRING).description("답변 생성자"),
                                                fieldWithPath("data[].modifiedBy").type(JsonFieldType.STRING).description("답변 수정자")
                                        )
                                )
                        ));
    }

    @Test
    void deleteAnswer() throws Exception{
        //given
        long answerId = 1L;
        //doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        //when
        ResultActions actions = mockMvc
                .perform(
                        delete("/answers/{id}",answerId));

        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("id").description("답변 ID"))
                                )
                        )
                );
    }

}
