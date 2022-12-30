package com.codestates.pre_028.stackoverflow_clone.User.Dto;

import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionTitleDto;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithAnswerAndQuestionResponseDto {
    private Long userId;
    private String nickname;
    private String email;
    private Integer reputation;

    private List<AnswerResponseDto> answers;
    private List<QuestionTitleDto> questions;

}
