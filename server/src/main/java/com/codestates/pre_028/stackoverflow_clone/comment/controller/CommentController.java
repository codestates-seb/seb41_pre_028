package com.codestates.pre_028.stackoverflow_clone.comment.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.service.UserService;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.mapper.CommentMapper;
import com.codestates.pre_028.stackoverflow_clone.comment.service.CommentService;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping
public class CommentController {

    private final CommentMapper mapper;
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentMapper mapper, CommentService commentService, UserService userService) {
        this.mapper = mapper;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/answers/{answer-id}/comments")
    public ResponseEntity postAnswerComment(@PathVariable("answer-id") @Positive Long answerId,
                                            @Valid @RequestBody CommentDto.AnswerPost commentPostDto){

        commentPostDto.setUserId(userService.getLoginUserWithToken().getUserId());
        commentPostDto.setAnswerId(answerId);

        Comment comment = commentService.createAnswerComment(mapper.commentAnswerPostDtoToComment(commentPostDto));


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToAnswerCommentResponseDto(comment)),HttpStatus.CREATED);
    }


    @PostMapping("/questions/{question-id}/comments")
    public ResponseEntity postQuestionComment(@PathVariable("question-id") @Positive Long questionId,
                                              @Valid @RequestBody CommentDto.QuestionPost commentPostDto){

        commentPostDto.setUserId(userService.getLoginUserWithToken().getUserId());
        commentPostDto.setQuestionId(questionId);
        Comment comment = commentService.createQuestionComment(mapper.commentQuestionPostDtoToComment(commentPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToQuestionCommentResponseDto(comment)),HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{answer-id}/comments/{comment-id}")
    public ResponseEntity patchAnswerComment(@PathVariable("answer-id") @Positive Long answerId, @PathVariable("comment-id") @Positive Long commentId,
                                             @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setAnswerId(answerId);
        commentPatchDto.setCommentId(commentId);
        commentPatchDto.setUserId(userService.getLoginUserWithToken().getUserId());
        Comment comment = mapper.commentPatchDtoToComment(commentPatchDto);
        comment.setUser(new User());
        comment.getUser().setUserId(commentPatchDto.getUserId());
        comment = commentService.updateComment(comment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToAnswerCommentResponseDto(comment)), HttpStatus.OK);
    }

    @PatchMapping("/questions/{question-id}/comments/{comment-id}")
    public ResponseEntity patchQuestionComment(@PathVariable("question-id") @Positive Long questionId, @PathVariable("comment-id") @Positive Long commentId,
                                               @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setUserId(userService.getLoginUserWithToken().getUserId());
        commentPatchDto.setQuestionId(questionId);
        commentPatchDto.setCommentId(commentId);
        Comment comment = mapper.commentPatchDtoToComment(commentPatchDto);
        comment.setUser(new User());
        comment.getUser().setUserId(commentPatchDto.getUserId());
        comment = commentService.updateComment(comment);


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToQuestionCommentResponseDto(comment)), HttpStatus.OK);
    }


 /*   코멘트는 Answer /Question 에 종속 적이므로 따로 GET 할 필요는 없음

 @GetMapping("/answers/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable("id") @Positive Long answerId){


        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentsToCommentResponseDtos(comments),
                        pageComments), HttpStatus.OK);
    }*/


/*    @GetMapping("/questions/{id}/comments")
    public ResponseEntity getQuestionComments(@PathVariable("id") @Positive Long questionId,
                                              @Positive @RequestParam int page,
                                              @Positive @RequestParam int size){
        Page<Comment> pageComments = commentService.findComments("Question", questionId, page -1 , size);
        List<Comment> comments = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentsToCommentResponseDtos(comments),
                        pageComments), HttpStatus.OK);

    }*/

    @DeleteMapping("/answers/{answer-id}/comments/{comment_id}")
    public ResponseEntity deleteAnswerComment(@PathVariable("answer-id") @Positive Long answerId,
                                              @PathVariable("comment_id") @Positive Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/questions/{question-id}/comments/{comment_id}")
    public ResponseEntity deleteQuestionComment(@PathVariable("question-id") @Positive Long answerId,
                                                @PathVariable("comment_id") @Positive Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
