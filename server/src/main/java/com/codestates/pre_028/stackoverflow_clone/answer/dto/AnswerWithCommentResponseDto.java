package com.codestates.pre_028.stackoverflow_clone.answer.dto;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class AnswerWithCommentResponseDto {
    private long answerId;
    private long questionId;
    private long userId;
    private String email;
    private String nickname;
    private String content;
    private List<CommentResponseDto> comments;
    private Answer.AnswerStatus answerStatus;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;

    public void setUser(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

    public void setQuestion(Question question){
        this.questionId = question.getQuestionId();
    }

    public String getAnswerStatus(){
        return answerStatus.getStatus();
    }
}

