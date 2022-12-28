package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

//question_id, user_id, title, content, tag, created_at, modified_at,created_by, modified_by
public class QuestionDto {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class QuestionPostDto {

        private long userId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private String tag;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class QuestionPatchDto {
        private long questionId;

        private long userId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private String tag;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class QuestionResponseDto {
        private long questionId;
        private String title;
        private String content;
        private String tag;

        private long userId;
        private String email;
        private String nickname;

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
}