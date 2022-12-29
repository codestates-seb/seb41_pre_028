package com.codestates.pre_028.stackoverflow_clone.Question.service;

import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.VoteQuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.Vote.entity.VoteQuestion;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<String> getContents(){
        return questionRepository.findAllDistinctContent();
    }

    @Transactional
    public Question createQuestion(Question question) {
        User user = userRepository.getReferenceById(question.getUser().getUserId());
        String tag = question.getTag();

        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));

        question.setUser(user);
        question.setTagList(tagList);

        Question savedQuestion = questionRepository.save(question);

        return savedQuestion;
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());


        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getTag())
                .ifPresent(tag -> findQuestion.setTag(tag));

        String tag = question.getTag();
        List<String> tagList = new ArrayList<>(Arrays.asList(tag.split(", ")));
        findQuestion.setTagList(tagList);


        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {

        return findVerifiedQuestion(questionId);

    }

    public void deleteQuestion(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        questionRepository.delete(findQuestion);

    }

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

    //퀘스쳔 필터 페이지네이션

    public Page<Question> findQuestionsFromFilter(String filter, int page, int size){
         if(filter.equals("unanswerd")) return questionRepository.findAllByAnswerListIsNull(PageRequest.of(page, size, Sort.by("questionId").descending()));

        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    //퀘스쳔 페이지네이션
    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    public Page<Question> findQuestionsByTag(String tag, int page, int size){
        return questionRepository.findAllByTagContaining(tag, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    // tag string -> tag 공백 혹은 , 로 끊어서 하나씩 테이블에 저장
    public String tagListToTag(QuestionDto.QuestionPostDto tagList){

        tagList.setTag(Arrays.stream(tagList.getTag().split(","))
                .map(tagA -> Arrays.stream(tagA.trim().split(" "))
                        .flatMap(tagB -> Arrays.stream(tagB.split(", "))))
                .flatMap(tagA-> tagA)
                .distinct()
                .filter(tagA -> !Objects.equals(tagA,""))
                .map(String::toLowerCase)
                .collect(Collectors.joining(", ")));

        return tagList.getTag();
    }

    public String tagListToTag(QuestionDto.QuestionPatchDto tagList){

        tagList.setTag(Arrays.stream(tagList.getTag().split(","))
                .map(tagA -> Arrays.stream(tagA.trim().split(" "))
                        .flatMap(tagB -> Arrays.stream(tagB.split(", "))))
                .flatMap(tagA-> tagA)
                .distinct()
                .filter(tagA -> !Objects.equals(tagA,""))
                .map(String::toLowerCase)
                .collect(Collectors.joining(", ")));

        return tagList.getTag();
    }

    public Question updateVote(VoteQuestionDto voteQuestionDto){
        Question findQuestion = findVerifiedQuestion(voteQuestionDto.getQuestionId());
        VoteQuestion voteQuestion = findQuestion.getVote();

        if(findQuestion.getUser().getUserId() == voteQuestionDto.getUserId()){
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }


        if(voteQuestion.getQuestionUserIds().contains(voteQuestionDto.getUserId())){
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }

        voteQuestion.setVoteNum(voteQuestion.getVoteNum() + voteQuestionDto.getVote());
        voteQuestion.setQuestionUserIds(voteQuestionDto.getUserId());

        findQuestion.setVote(voteQuestion);

        return questionRepository.save(findQuestion);
    }
}

