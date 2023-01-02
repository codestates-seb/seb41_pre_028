package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class QuestionTitleDto {

    private long questionId;
    private String title;
}
