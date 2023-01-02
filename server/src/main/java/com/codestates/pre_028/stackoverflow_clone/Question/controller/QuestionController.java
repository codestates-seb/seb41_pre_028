package com.codestates.pre_028.stackoverflow_clone.Question.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionPaginationDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.VoteQuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.mapper.QuestionMapper;
import com.codestates.pre_028.stackoverflow_clone.Question.service.PaginationService;
import com.codestates.pre_028.stackoverflow_clone.Question.service.QuestionService;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;
    private final PaginationService paginationService;
    private final UserService userService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService,
                              PaginationService paginationService,
                              UserService userService,
                              QuestionMapper mapper) {

        this.questionService = questionService;
        this.paginationService = paginationService;
        this.userService = userService;
        this.mapper = mapper;
    }

    //질문 등록
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.QuestionPostDto questionDto){

        questionDto.setUserId(userService.getLoginUserWithToken().getUserId()); //로그인 유저를 가져와서 글 작성

        String tag  = questionService.tagListToTag(questionDto);
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionDto));

        question.setTag(tag);


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)), HttpStatus.CREATED);
    }

    //질문 수정
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(
            @PathVariable("question-id") long questionId,
            @RequestBody @Valid QuestionDto.QuestionPatchDto questionPatchDto){

        questionPatchDto.setQuestionId(questionId);
        questionPatchDto.setUserId(userService.getLoginUserWithToken().getUserId());
        Question question = mapper.questionPatchDtoToQuestion(questionPatchDto);
        question.setUser(new User());
        question.getUser().setUserId(questionPatchDto.getUserId());

       Question response = questionService.updateQuestion(question);

       String tag  = questionService.tagListToTag(questionPatchDto);
       response.setTag(tag);


        return new ResponseEntity<>(
                    new SingleResponseDto<>(mapper.questionToQuestionResponseDto(response)), HttpStatus.OK);


    }

    //질문 상세 조회
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId){
        Question response = questionService.findQuestion(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(response)),HttpStatus.OK);
    }

    //질문 전체 조회
    @GetMapping
    public ResponseEntity getQuestionPagination(
            @RequestParam(required = false , value = "filter")
            String filter,
            @RequestParam int page,
            @Positive @RequestParam int size){

        if(filter == null) filter = "newest";
        if(!filter.equals("newest") && !filter.equals("unanswerd"))
            return new ResponseEntity<>("잘못된 filter 입니다", HttpStatus.BAD_REQUEST);


        Page<Question> pageQuestions = questionService.findQuestionsFromFilter(filter, page , size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionPaginationDto> responseDtos = mapper.questionToQuestionResponseDto(questions);
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pageQuestions.getTotalPages());

        return new ResponseEntity<>(
                new MultiResponseDto<>(responseDtos, pageQuestions, barNumber), HttpStatus.OK
        );
    }

    //질문 삭제
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId){

        questionService.deleteQuestion(questionId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{tag}")
    public ResponseEntity searchQuestionWithTag(
            @PathVariable("tag") String tag,
            @RequestParam int page,
            @RequestParam(defaultValue = "15") int size){

        Page<Question> pageQuestions = questionService.findQuestionsByTag(tag, page, size);
        List<Question> questionsWithTag = pageQuestions.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pageQuestions.getTotalPages());

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionToQuestionResponseDto(questionsWithTag), pageQuestions, barNumber), HttpStatus.OK);
    }

    @PatchMapping("/{question-id}/question_vote")
    public ResponseEntity voteToQuestion(@PathVariable("question-id") Long questionId,
                                         @Valid @RequestBody VoteQuestionDto voteQuestionDto){
        voteQuestionDto.setUserId(userService.getLoginUserWithToken().getUserId());
        voteQuestionDto.setQuestionId(questionId);
        Question question = questionService.updateVote(voteQuestionDto);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)), HttpStatus.OK);
    }
}
