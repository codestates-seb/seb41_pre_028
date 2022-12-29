package com.codestates.pre_028.stackoverflow_clone.Search.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionPaginationDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.mapper.QuestionMapper;
import com.codestates.pre_028.stackoverflow_clone.Search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {

    private final SearchService searchService;
    private final QuestionMapper mapper;

    @GetMapping
    public ResponseEntity searchQuestionWithKeyword(
            @RequestParam Map<String, String> params,
            @PageableDefault(size = 15) Pageable pageable){
        String key = (String) params
                .keySet()
                .iterator()
                .next();
        String value = params.get(key);
        System.out.println("key : "+key+" value : "+value);
        Page<Question> pageQuestions = searchService.searchQuestion(key,value,pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionPaginationDto> responsDtos = mapper.questionToQuestionWithKeywordResponseDto(questions);


        return new ResponseEntity<>(
                new MultiResponseDto<>(responsDtos, pageQuestions), HttpStatus.OK);

    }


/*    @GetMapping("/keyword")
    public ResponseEntity searchQuestionWithKeyword(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 15) Pageable pageable){

        Page<Question> pageQuestions = searchService.searchQuestion(keyword,pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionPaginationDto> responsDtos = mapper.questionToQuestionWithKeywordResponseDto(questions);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responsDtos, pageQuestions), HttpStatus.OK);

    }*/
}