package com.codestates.pre_028.stackoverflow_clone.answer.dto;

import com.codestates.pre_028.stackoverflow_clone.Vote.entity.VoteAnswer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AnswerResponseDto {
    private long answerId;
    private long userId;
    private long questionId;
    private String nickname;
    private String content;
    private VoteAnswer vote;

    public Long getVote(){
        return this.vote.getVoteNum();
    }
}
