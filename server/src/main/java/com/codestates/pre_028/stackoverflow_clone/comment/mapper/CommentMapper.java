package com.codestates.pre_028.stackoverflow_clone.comment.mapper;
//추후주석제거
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
//import com.codestates.pre_028.stackoverflow_clone.user.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.question.entity.Qusetion;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPatchDtoToComment(CommentDto.Patch commentPatchDto);
    List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments);


    default Comment commentPostDtoToComment(CommentDto.Post commentPostDto){
        Comment comment = new Comment();
//        User user = new User();
//        Question question = new Question();
        Answer answer = new Answer();

//        user.setUserId(commentPostDto.getUserId());
//        question.setQuestionId(commentPostDto.getQuestionId());
        answer.setAnswerId(commentPostDto.getAnswerId());

//        comment.setUser(user);
        comment.setAnswer(answer);
//        comment.setQuestion(question);
        comment.setContent(commentPostDto.getContent());
        return comment;
    }

    default CommentDto.Response commentToCommentResponseDto(Comment comment){
        CommentDto.Response commentResponseDto = new CommentDto.Response();
        commentResponseDto.setCommentId(comment.getCommentId());
//        commentResponseDto.setUser(comment.getUser());
//        commentResponseDto.setQuestion(comment.getQuestion());
        commentResponseDto.setAnswer(comment.getAnswer());
        commentResponseDto.setContent(comment.getContent());

//        commentResponseDto.setCreatedAt(comment.getCreatedAt());
//        commentResponseDto.setModifiedAt(comment.getModifiedAt);
//        commentResponseDto.setCreatedBy(comment.getCreatedBy());
//        commentResponseDto.setModifiedBy(comment.getModifiedBy());

        return commentResponseDto;
    }

}