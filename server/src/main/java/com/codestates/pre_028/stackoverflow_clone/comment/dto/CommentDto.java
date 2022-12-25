package com.codestates.pre_028.stackoverflow_clone.comment.dto;
//추후 주석제거
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
//import com.codestates.pre_028.stackoverflow_clone.user.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.question.entity.Qusetion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Setter //test stub을 위해 추가
    @Getter
    public static class Post{
        @Positive
        private Long userId;

        @Positive
        private Long questionId;

        @Positive
        private Long answerId;

        @NotBlank
        private String content;

    }

    @Setter //test stub을 위해 추가
    @Getter
    public static class Patch{
        private Long commentId;
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

//        public void setUser(User user){
//            this.userId = user.getUserId();
//        }
//        public void setQuestion(Question question){
//            this.questionId = question.getQuestionId();
//        }

        //for dto
        public void setAnswer(Answer answer){
            this.answerId = answer.getAnswerId();
        }
    }
}
