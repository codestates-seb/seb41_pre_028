package com.codestates.pre_028.stackoverflow_clone.Question.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionPaginationDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            @RequestBody QuestionDto.QuestionPatchDto questionPatchDto){

        questionPatchDto.setQuestionId(questionId);


        Question response = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));
        User createUser = userService.findVerifiedUser(response.getUser().getUserId()); //글 작성 유저

        if (!Objects.equals(userService.getLoginUserWithToken().getUserId(), createUser.getUserId())) {  // 로그인 유저 검증
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

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
    public ResponseEntity getQuestionPagination(@RequestParam int page,
                                                @Positive @RequestParam int size){

        Page<Question> pageQuestions = questionService.findQuestions(page , size);
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


    @GetMapping("/keyword")
    public ResponseEntity searchQuestionWithKeyword(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 15) Pageable pageable){



        Page<Question> pageQuestions = questionService.searchQuestion(keyword,pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionPaginationDto> responsDtos = mapper.questionToQuestionWithKeywordResponseDto(questions);


        return new ResponseEntity<>(
                new MultiResponseDto<>(responsDtos, pageQuestions), HttpStatus.OK);

    }

    @GetMapping("/{tag}")
    public ResponseEntity searchQuestionWithTag(
            @PathVariable("tag") String tag,
            @RequestParam int page,
            @RequestParam(defaultValue = "15") int size){

        Page<Question> pageQuestions = questionService.findAllbyTag(tag, page, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionToQuestionResponseDto(questions), pageQuestions), HttpStatus.OK);
    }
}
