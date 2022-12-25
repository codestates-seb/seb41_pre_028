package com.codestates.pre_028.stackoverflow_clone.comment.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping
public class CommentController {

    @PostMapping("/answers/{id}/comments")
    public ResponseEntity postAnswerComment(@PathVariable("id") @Positive Long answerId,
                                            @Valid @RequestBody CommentDto.Post commentPostDto){

        CommentDto.Response response = new CommentDto.Response();
        response.setCommentId(1L);
        response.setAnswerId(answerId);
        response.setUserId(1L);
        response.setContent("stub answerComment content");
        response.setCreatedAt(LocalDateTime.now());
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("stub_answerComment_creator");
        response.setModifiedBy("stub_answerComment_modifier");

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }


    @PostMapping("/questions/{id}/comments")
    public ResponseEntity postQuestionComment(@PathVariable("id") @Positive Long questionId,
                                              @Valid @RequestBody CommentDto.Post commentPostDto){
        CommentDto.Response response = new CommentDto.Response();
        response.setCommentId(1L);
        response.setQuestionId(1L);
        response.setUserId(1L);
        response.setContent("stub questionComment content");
        response.setCreatedAt(LocalDateTime.now());
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("stub_questionComment_creator");
        response.setModifiedBy("stub_questionComment_modifier");
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity patchAnswerComment(@PathVariable("id") @Positive Long answerId,
                                             @Valid @RequestBody CommentDto.Patch commentPatchDto){

        CommentDto.Response response = new CommentDto.Response();
        response.setCommentId(1L);
        response.setAnswerId(1L);
        response.setUserId(1L);
        response.setContent("stub answerComment content");
        response.setCreatedAt(LocalDateTime.now());
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("stub_answerComment_creator");
        response.setModifiedBy("stub_answerComment_modifier");

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity patchQuestionComment(@PathVariable("id") @Positive Long questionId,
                                               @Valid @RequestBody CommentDto.Patch commentPatchDto){
        CommentDto.Response response = new CommentDto.Response();
        response.setCommentId(1L);
        response.setQuestionId(1L);
        response.setUserId(1L);
        response.setContent("stub questionComment content");
        response.setCreatedAt(LocalDateTime.now());
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("stub_questionComment_creator");
        response.setModifiedBy("stub_questionComment_modifier");
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/answers/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable("id") @Positive Long answerId){

        CommentDto.Response response1 = new CommentDto.Response();
        response1.setCommentId(1L);
        response1.setAnswerId(1L);
        response1.setUserId(1L);
        response1.setContent("stub answerComment content");
        response1.setCreatedAt(LocalDateTime.now());
        response1.setModifiedAt(LocalDateTime.now());
        response1.setCreatedBy("stub_answerComment_creator");
        response1.setModifiedBy("stub_answerComment_modifier");

        CommentDto.Response response2 = new CommentDto.Response();
        response2.setCommentId(2L);
        response2.setAnswerId(2L);
        response2.setUserId(2L);
        response2.setContent("stub answerComment content2");
        response2.setCreatedAt(LocalDateTime.now());
        response2.setModifiedAt(LocalDateTime.now());
        response2.setCreatedBy("stub_answerComment_creator2");
        response2.setModifiedBy("stub_answerComment_modifier2");

        return new ResponseEntity<>(new MultiResponseDto(Arrays.asList(response1,response2)), HttpStatus.OK);
    }

    @GetMapping("/questions/{id}/comments")
    public ResponseEntity getQuestionComments(@PathVariable("id") @Positive Long questionId){
        CommentDto.Response response1 = new CommentDto.Response();
        response1.setCommentId(1L);
        response1.setQuestionId(1L);
        response1.setUserId(1L);
        response1.setContent("stub questionComment content");
        response1.setCreatedAt(LocalDateTime.now());
        response1.setModifiedAt(LocalDateTime.now());
        response1.setCreatedBy("stub_questionComment_creator");
        response1.setModifiedBy("stub_questionComment_modifier");

        CommentDto.Response response2 = new CommentDto.Response();
        response2.setCommentId(2L);
        response2.setQuestionId(2L);
        response2.setUserId(2L);
        response2.setContent("stub questionComment content");
        response2.setCreatedAt(LocalDateTime.now());
        response2.setModifiedAt(LocalDateTime.now());
        response2.setCreatedBy("stub_questionComment_creator2");
        response2.setModifiedBy("stub_questionComment_modifier2");
        return new ResponseEntity<>(new MultiResponseDto(Arrays.asList(response1,response2)), HttpStatus.OK);
    }

    @DeleteMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity deleteAnswerComment(@PathVariable("id") @Positive Long answerId,
                                              @PathVariable("comment_id") @Positive Long commentId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity deleteQuestionComment(@PathVariable("id") @Positive Long answerId,
                                                @PathVariable("comment_id") @Positive Long commentId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
