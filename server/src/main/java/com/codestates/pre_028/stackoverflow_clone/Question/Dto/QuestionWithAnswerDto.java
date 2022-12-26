package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionWithAnswerDto {
    private long questionId;
    private long userId;

    private String email;
    private String nickname;
    private String content;
    private List<AnswerResponseDto> answerResponseDtos;

    private List<CommentResponseDto> comments;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;

    public void setUser(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

}
