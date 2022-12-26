package com.codestates.pre_028.stackoverflow_clone.comment.controller;

import com.codestates.pre_028.stackoverflow_clone.Dto.MultiResponseDto;
import com.codestates.pre_028.stackoverflow_clone.Dto.SingleResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.mapper.CommentMapper;
import com.codestates.pre_028.stackoverflow_clone.comment.repository.CommentRepository;
import com.codestates.pre_028.stackoverflow_clone.comment.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
                                            @Valid @RequestBody CommentDto.Post commentPostDto){

        commentPostDto.setAnswerId(answerId);
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(commentPostDto));


        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)),HttpStatus.CREATED);
    }


    @PostMapping("/questions/{id}/comments")
    public ResponseEntity postQuestionComment(@PathVariable("id") @Positive Long questionId,
                                              @Valid @RequestBody CommentDto.Post commentPostDto){

        commentPostDto.setQuestionId(questionId);
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(commentPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)),HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{id}/comments/{comment_id}")
    public ResponseEntity patchAnswerComment(@PathVariable("id") @Positive Long answerId, @PathVariable("comment-id") @Positive Long commentId,
                                             @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setAnswerId(answerId);
        commentPatchDto.setCommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(commentPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)), HttpStatus.OK);
    }

    @PatchMapping("/questions/{id}/comments/{comment_id}")
    public ResponseEntity patchQuestionComment(@PathVariable("id") @Positive Long questionId, @PathVariable("comment-id") @Positive Long commentId,
                                               @Valid @RequestBody CommentDto.Patch commentPatchDto){

        commentPatchDto.setQuestionId(questionId);
        commentPatchDto.setCommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(commentPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)), HttpStatus.OK);
    }

/* 겟매핑 구현중

    @GetMapping("/answers/{id}/comments")
    public ResponseEntity getAnswerComments(@PathVariable("id") @Positive Long answerId,
                                            @Positive @RequestParam int page,
                                            @Positive @RequestParam int size){
        Page<Comment> pageComments = commentService.findComments("Answer", answerId, page -1 , size);
        List<Comment> comments = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentsToCommentResponseDtos(comments),
                        pageComments), HttpStatus.OK);
    }
*/

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
