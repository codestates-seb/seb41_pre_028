package com.codestates.pre_028.stackoverflow_clone.comment.controller;

import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping
public class CommentController {

    @PostMapping("/answers/{id}/comments")
    public ResponseEntity postAnswerComment(@Valid @RequestBody CommentDto.Post commentPostDto){

        CommentDto.Response response = new CommentDto.Response(1,1,1,1,
                "stub acomment", LocalDateTime.now(),LocalDateTime.now(),
                "stubacommentUser","stubacommentUser");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/questions/{id}/comments")
    public ResponseEntity postQuestionComment(){
        CommentDto.Response response = new CommentDto.Response(1,1,1,1,
                "stub qcomment", LocalDateTime.now(),LocalDateTime.now(),
                "stubqcommentUser","stubqcommentUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity patchAnswerComment(){
        CommentDto.Response response = new CommentDto.Response(1,1,1,1,
                "stub acomment", LocalDateTime.now(),LocalDateTime.now(),
                "stubacommentUser","stubacommentUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity patchQuestionComment(){
        CommentDto.Response response = new CommentDto.Response(1,1,1,1,
                "stub qcomment", LocalDateTime.now(),LocalDateTime.now(),
                "stubqcommentUser","stubqcommentUser");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/answers/{id}/comments")
    public ResponseEntity getAnswerComments(){
        CommentDto.Response response1 = new CommentDto.Response(1,1,1,1,
                "stub acomment1", LocalDateTime.now(),LocalDateTime.now(),
                "stubacommentUser1","stubacommentUser1");
        CommentDto.Response response2 = new CommentDto.Response(1,1,1,1,
                "stub acomment2", LocalDateTime.now(),LocalDateTime.now(),
                "stubacommentUser2","stubacommentUser2");
        return new ResponseEntity<>(Arrays.asList(response1,response2), HttpStatus.OK);
    }

    @GetMapping("/questions/{id}/comments")
    public ResponseEntity getQuestionComments(){
        CommentDto.Response response1 = new CommentDto.Response(1,1,1,1,
                "stub qcomment1", LocalDateTime.now(),LocalDateTime.now(),
                "stubqcommentUser1","stubqcommentUser1");
        CommentDto.Response response2 = new CommentDto.Response(1,1,1,1,
                "stub qcomment2", LocalDateTime.now(),LocalDateTime.now(),
                "stubqcommentUser2","stubqcommentUser2");
        return new ResponseEntity<>(Arrays.asList(response1,response2), HttpStatus.OK);
    }

    @DeleteMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity deleteAnswerComments(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity deleteQuestionComments(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
