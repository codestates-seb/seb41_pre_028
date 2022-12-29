package com.codestates.pre_028.stackoverflow_clone.Question.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

        @NotBlank(message = "태그는 공백이 아니어야 합니다.")
        private String tag;
    }

}