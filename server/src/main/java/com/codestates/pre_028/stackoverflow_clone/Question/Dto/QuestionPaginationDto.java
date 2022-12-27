package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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