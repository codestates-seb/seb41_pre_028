package com.codestates.pre_028.stackoverflow_clone.answer.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AnswerResponseDto {
    private long userId;
    private long questionId;
    private String nickname;
    private String content;
}
