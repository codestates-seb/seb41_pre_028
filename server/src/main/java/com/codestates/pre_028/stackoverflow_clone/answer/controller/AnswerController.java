package com.codestates.pre_028.stackoverflow_clone.answer.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.VoteAnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.mapper.AnswerMapper;
import com.codestates.pre_028.stackoverflow_clone.answer.service.AnswerService;
import com.codestates.pre_028.stackoverflow_clone.comment.service.CommentService;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {
    private final AnswerMapper mapper;
    private final AnswerService answerService;
    private final CommentService commentService;
    private final UserService userService;

    public AnswerController(
            AnswerMapper mapper,
            AnswerService answerService,
            CommentService commentService,
            UserService userService) {
        this.mapper = mapper;
        this.answerService = answerService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto){

        answerPostDto.setUserId(userService.getLoginUserWithToken().getUserId());        // 로그인 유저 가져오기
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.answerWithCommentToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerPatchDto){
        answerPatchDto.setAnswerId(answerId);
        answerPatchDto.setUserId(userService.getLoginUserWithToken().getUserId());
        Answer answer = mapper.answerPatchDtoToAnswer(answerPatchDto);
        answer.setUser(new User());
        answer.getUser().setUserId(answerPatchDto.getUserId());
        answer = answerService.updateAnswer(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.answerWithCommentToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId){

        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.answerWithCommentToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

/*    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Answer> pageAnswers = answerService.findAnswers(page-1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.answersToAnswerResponseDtos(answers),
                pageAnswers),
                HttpStatus.OK);
    }*/

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId){
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{answer-id}/answer_vote")
    public ResponseEntity voteToAnswer(@PathVariable("answer-id") Long answerId,
                                       @Valid @RequestBody VoteAnswerDto voteAnswerDto){
        voteAnswerDto.setUserId(userService.getLoginUserWithToken().getUserId());
        voteAnswerDto.setAnswerId(answerId);
        Answer answer = answerService.updateVote(voteAnswerDto);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.answerWithCommentToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

}
