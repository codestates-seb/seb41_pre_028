package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.Vote.entity.VoteQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionPaginationDto {
    private long questionId;
    private String title;
    private String content;
    private long userId;
    private String email;
    private String nickname;
    private String tag;
    private List<String> tagList;
    private VoteQuestion vote;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;

    public void setUser(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

    public Long getVote(){
        return this.vote.getVoteNum();
    }
}
