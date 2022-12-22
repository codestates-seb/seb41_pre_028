package com.codestates.pre_028.stackoverflow_clone.answer.controller;

import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.mapper.AnswerMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {
    private final AnswerMapper mapper;

    public AnswerController(AnswerMapper mapper){
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto){

//        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
//        AnswerDto.Response response = mapper.answerToAnswerResponseDto(answer);
        AnswerDto.Response response = new AnswerDto.Response(1,1,1,"stub data", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser","stubUser");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchAnswer(@PathVariable("id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerPatchDto){
//        answerPatchDto.setAnswerId(answerId);
//        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));
//        AnswerDto.Response response = mapper.answerToAnswerResponseDto(answer);
        AnswerDto.Response response = new AnswerDto.Response(answerId,1,1,"stub data", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser","stubUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}/answer_vote")
    public ResponseEntity voteAnswer(@PathVariable("id") @Positive long answerId,
                                     @Valid @RequestBody AnswerDto.VotePatch votePatchDto){
        //투표를 +-로 Vote_status를 정함.
        //Answer answer = answerService.updateVoteAnswer(answer);
        AnswerDto.Response response = new AnswerDto.Response(answerId,1,1,"stub data", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser","stubUser");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAnswer(@PathVariable("id") @Positive long answerId){

        //Answer answer = answerService.findAnswer(answerId);
        AnswerDto.Response response = new AnswerDto.Response(answerId,1,1,"stub data", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser","stubUser");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(){
        AnswerDto.Response response1 = new AnswerDto.Response(1,1,1,"stub data1", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser1","stubUser1");
        AnswerDto.Response response2 = new AnswerDto.Response(2,2,2,"stub data2", Answer.AnswerStatus.ANSWER_NORMAL,1, LocalDateTime.now(),LocalDateTime.now(),"stubUser2","stubUser2");
        return new ResponseEntity<>(Arrays.asList(response1,response2),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
