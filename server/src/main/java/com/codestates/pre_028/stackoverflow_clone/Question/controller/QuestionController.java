package com.codestates.pre_028.stackoverflow_clone.Question.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionPaginationDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.mapper.QuestionMapper;
import com.codestates.pre_028.stackoverflow_clone.Question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService ;
        this.mapper = mapper;
    }
    //질문 등록
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.QuestionPostDto questionDto){

        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionDto));

        return new ResponseEntity<>(mapper.questionToQuestionResponseDto(question), HttpStatus.CREATED);
    }

    //질문 수정
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(
            @PathVariable("question-id") long questionId,
            @RequestBody QuestionDto.QuestionPatchDto questionPatchDto){
        questionPatchDto.setQuestionId(questionId);
        Question response = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(mapper.questionToQuestionResponseDto(response),HttpStatus.OK);
    }

    //질문 상세 조회
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId){
        Question response = questionService.findQuestion(questionId);

        return new ResponseEntity<>(mapper.questionToQuestionResponseDto(response),HttpStatus.OK);
    }

    //질문 전체 조회
    @GetMapping
    public ResponseEntity getQuestionPagination(@Positive @RequestParam int page,
                                                @Positive @RequestParam int size){

        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionPaginationDto> responseDtos = mapper.questionToQuestionResponseDto(questions);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responseDtos, pageQuestions), HttpStatus.OK
        );
    }

    //질문 삭제
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId){

        questionService.deleteQuestion(questionId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
