package com.codestates.pre_028.stackoverflow_clone.comment.dto;
//추후 주석제거

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Setter //test stub을 위해 추가
    @Getter
    public static class QuestionPost{
        @Positive
        private Long userId;


        private Long questionId;

        @NotBlank
        private String content;

    }
    @Setter //test stub을 위해 추가
    @Getter
    public static class AnswerPost{
        @Positive
        private Long userId;


        private Long answerId;

        @NotBlank
        private String content;

    }


    @Setter //test stub을 위해 추가
    @Getter
    public static class Patch{
        private Long commentId;

        private Long questionId;

        private Long answerId;

        private String content;
    }



    @Getter
    @Setter
    public static class Response{
        private Long commentId;
        private Long answerId;
        private Long userId;
        private Long questionId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String createdBy;
        private String modifiedBy;


        //for dto
        public void setQuestion(Question question){
            this.questionId = question.getQuestionId();
        }

        public void setAnswer(Answer answer){
            this.answerId = answer.getAnswerId();
        }

        public void setUser(User user) {
            this.userId = user.getUserId();
        }
    }
}
