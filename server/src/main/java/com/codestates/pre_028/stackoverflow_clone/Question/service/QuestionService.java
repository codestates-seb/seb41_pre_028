package com.codestates.pre_028.stackoverflow_clone.Question.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question createQuestion(Question question) {
        User user = userRepository.getReferenceById(question.getUser().getUserId());

        question.setUser(user);

        Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

    public Question updateQuestion(Question question) {
        Question updatedQuestion = question;
        return updatedQuestion;
    }

    public Question findQuestion(long questionId) {

        return null;

    }

    public List<Question> findQuestions() {
        return null;
    }

    public void deleteQuestion(long questionId) {

    }
}
