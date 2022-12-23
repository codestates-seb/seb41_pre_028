package com.codestates.pre_028.stackoverflow_clone.answer.dto;
//추후 주석제거
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer.AnswerStatus;
//import com.codestates.pre_028.stackoverflow_clone.user.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.question.entity.Qusetion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


public class AnswerDto {

    @NoArgsConstructor  //test를 위해 추가
    @AllArgsConstructor //test를 위해 추가
    @Getter
    public static class Post{
        @Positive
        private long userId;

        @Positive
        private long questionId;

        @NotBlank
        private String content;
    }

    @NoArgsConstructor  //test를 위해 추가
    @AllArgsConstructor //test를 위해 추가
    @Getter
    @Setter
    public static class Patch{

        private long answerId;
        private AnswerStatus answerStatus;
        private String content;
    }


    @NoArgsConstructor  //stub controller 위해
    @AllArgsConstructor //stub controller 위해
    @Getter
    @Setter
    public static class Response{
        private long answerId;
        private long questionId;
        private long userId;
        private String content;
        private AnswerStatus answerStatus;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String createdBy;
        private String modifiedBy;

//        public void setQuestion(Question question){
//            this.questionId = question.getQuestionId();
//        }
//        public void setUser(User user){
//            this.userId= user.getUserId();
//        }

        public String getAnswerStatus(){
            return answerStatus.getStatus();
        }
    }

}
