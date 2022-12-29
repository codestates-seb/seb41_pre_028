package com.codestates.pre_028.stackoverflow_clone.Question.Dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteQuestionDto {
    Long questionId;

    Long userId;

    //1 or -1만
    @NotNull
    Long vote;
}
