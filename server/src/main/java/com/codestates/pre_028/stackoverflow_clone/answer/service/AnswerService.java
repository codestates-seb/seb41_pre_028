package com.codestates.pre_028.stackoverflow_clone.answer.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.Vote.entity.VoteAnswer;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.VoteAnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.repository.AnswerRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;


    public Answer createAnswer(Answer answer){
        User user = userRepository.getReferenceById(answer.getUser().getUserId());
        Question question = questionRepository.getReferenceById(answer.getQuestion().getQuestionId());

        answer.setUser(user);
        answer.setQuestion(question);

        Answer savedAnswer = answerRepository.save(answer);
        return savedAnswer;
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getAnswerStatus())
                .ifPresent(answerStatus -> findAnswer.setAnswerStatus(answerStatus));
        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        if(!Objects.equals(findAnswer.getUser().getUserId(), answer.getUser().getUserId()))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);

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
    }

    //Update를 위한 메서드
    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

    public Answer updateVote(VoteAnswerDto voteAnswerDto){
        Answer findAnswer = findVerifiedAnswer(voteAnswerDto.getAnswerId());
        VoteAnswer voteAnswer = findAnswer.getVoteAnswer();

        if(findAnswer.getUser().getUserId() == voteAnswerDto.getUserId()){
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        if(voteAnswer.getAnswerUserIds().contains(voteAnswerDto.getUserId())){
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }

        voteAnswer.setVoteNum(voteAnswer.getVoteNum() + voteAnswerDto.getVote());
        voteAnswer.setAnswerUserIds(voteAnswerDto.getUserId());

        findAnswer.setVoteAnswer(voteAnswer); //이 라인 생략가능한지 체크할것

        return answerRepository.save(findAnswer);
    }
}
