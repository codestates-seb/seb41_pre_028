package com.codestates.pre_028.stackoverflow_clone.Question.service;

import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

}

