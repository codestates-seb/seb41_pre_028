package com.codestates.pre_028.stackoverflow_clone.comment.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.mapper.CommentMapper;
import com.codestates.pre_028.stackoverflow_clone.comment.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CommentController {

    private final CommentMapper mapper;
    private final CommentService commentService;

    public CommentController(CommentMapper mapper, CommentService commentService){
        this.mapper = mapper;
        this.commentService = commentService;
    }

    @PostMapping("/answers/{id}/comments")
    public ResponseEntity postAnswerComment(@PathVariable("id") @Positive Long answerId,
                                            @Valid @RequestBody CommentDto.AnswerPost commentPostDto){

        commentPostDto.setAnswerId(answerId);
        Comment comment = commentService.createAnswerComment(mapper.commentAnswerPostDtoToComment(commentPostDto));


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToAnswerCommentResponseDto(comment)),HttpStatus.CREATED);
    }


    @PostMapping("/questions/{id}/comments")
    public ResponseEntity postQuestionComment(@PathVariable("id") @Positive Long questionId,
                                              @Valid @RequestBody CommentDto.QuestionPost commentPostDto){

        commentPostDto.setQuestionId(questionId);
        Comment comment = commentService.createQuestionComment(mapper.commentQuestionPostDtoToComment(commentPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToQuestionCommentResponseDto(comment)),HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{id}/comments/{comment-id}")
    public ResponseEntity patchAnswerComment(@PathVariable("id") @Positive Long answerId, @PathVariable("comment-id") @Positive Long commentId,
                                             @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setAnswerId(answerId);
        commentPatchDto.setCommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(commentPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToAnswerCommentResponseDto(comment)), HttpStatus.OK);
    }

    @PatchMapping("/questions/{id}/comments/{comment-id}")
    public ResponseEntity patchQuestionComment(@PathVariable("id") @Positive Long questionId, @PathVariable("comment-id") @Positive Long commentId,
                                               @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setQuestionId(questionId);
        commentPatchDto.setCommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(commentPatchDto));

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

    @DeleteMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity deleteAnswerComment(@PathVariable("id") @Positive Long answerId,
                                              @PathVariable("comment_id") @Positive Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity deleteQuestionComment(@PathVariable("id") @Positive Long answerId,
                                                @PathVariable("comment_id") @Positive Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
