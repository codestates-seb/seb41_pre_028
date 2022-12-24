package com.codestates.pre_028.stackoverflow_clone.Question.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    public Question createQuestion(Question question) {
        Question createdQuestion = question;
        return createdQuestion;
    }

    public Question updateQuestion(Question question) {
        Question updatedQuestion = question;
        return updatedQuestion;
    }

    public Question findQuestion(long questionId) {
        Question question = new Question("제목","내용","태그");
        return question;

    }

    public List<Question> findQuestions() {
        List<Question> questions = List.of(
                new Question("제목1","내용1","태그1"),
                new Question("제목2","내용2","태그2")
        );
        return questions;
    }

    public void deleteQuestion(long questionId) {

    }
}
