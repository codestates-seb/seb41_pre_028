package com.codestates.pre_028.stackoverflow_clone.answer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteAnswerDto {

    Long answerId;

    Long userId;

    //1 or -1만
    @NotNull
    Long vote;

}
