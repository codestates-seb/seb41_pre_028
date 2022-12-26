package com.codestates.pre_028.stackoverflow_clone.answer.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.repository.AnswerRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {

    AnswerRepository answerRepository;
    UserRepository userRepository;
    QuestionRepository questionRepository;


    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Answer createAnswer(Answer answer){
        verifyExistAnswer(answer.getAnswerId());
        User user = userRepository.getReferenceById(answer.getUser().getUserId());
        Question question = questionRepository.getReferenceById(answer.getQuestion().getQuestionId());

        answer.setQuestion(question);
        answer.setUser(user);

        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer;
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getAnswerStatus())
                .ifPresent(answerStatus -> findAnswer.setAnswerStatus(answerStatus));
        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(Long answerId){
        return findVerifiedAnswer(answerId);
    }

    public Page<Answer> findAnswers(int page, int size){
        return answerRepository.findAll(PageRequest.of(page,size, Sort.by("answerId").descending()));
    }

    public void deleteAnswer(Long answerId){
        Answer findAnswer = findVerifiedAnswer(answerId);
        findAnswer.setAnswerStatus(Answer.AnswerStatus.ANSWER_DELETED);
        answerRepository.save(findAnswer);
        //댓글제거도추후구현
    }

    //Create를 위한 메서드
    private void verifyExistAnswer(Long answerId){
        // 회원이 존재하는지 확인 후에 구현
    }

    //Update를 위한 메서드
    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }
}
