package com.codestates.pre_028.stackoverflow_clone.answer.mapper;


import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerWithCommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {


    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);

    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto){
        Answer answer = new Answer();
        User user = new User();
        user.setUserId(answerPostDto.getUserId());


        Question question = new Question();
        question.setQuestionId(answerPostDto.getQuestionId());
        answer.setQuestion(question);
        answer.setUser(user);
        answer.setContent(answerPostDto.getContent());

        return answer;
    }

    default AnswerDto.Response answerToAnswerResponseDto(Answer answer){
        AnswerDto.Response answerDtoResponse = new AnswerDto.Response();

        answerDtoResponse.setAnswerId(answer.getAnswerId());
        answerDtoResponse.setUser(answer.getUser());
        answerDtoResponse.setQuestion(answer.getQuestion());

        answerDtoResponse.setContent(answer.getContent());
        answerDtoResponse.setAnswerStatus(answer.getAnswerStatus());


        answerDtoResponse.setCreatedAt(answer.getCreatedAt());
        answerDtoResponse.setModifiedAt(answer.getModifiedAt());
        answerDtoResponse.setCreatedBy(answer.getCreatedBy());
        answerDtoResponse.setModifiedBy(answer.getModifiedBy());

        return answerDtoResponse;
    }

    default AnswerWithCommentResponseDto answerWithCommentToAnswerResponseDto(Answer answer){
        List<Comment> comments = answer.getComments();

        AnswerWithCommentResponseDto answerWithCommentResponseDto = new AnswerWithCommentResponseDto();

        answerWithCommentResponseDto.setAnswerId(answer.getAnswerId());
        answerWithCommentResponseDto.setUser(answer.getUser());
        answerWithCommentResponseDto.setQuestion(answer.getQuestion());


        answerWithCommentResponseDto.setContent(answer.getContent());
        answerWithCommentResponseDto.setAnswerStatus(answer.getAnswerStatus());


        answerWithCommentResponseDto.setCreatedAt(answer.getCreatedAt());
        answerWithCommentResponseDto.setModifiedAt(answer.getModifiedAt());
        answerWithCommentResponseDto.setCreatedBy(answer.getCreatedBy());
        answerWithCommentResponseDto.setModifiedBy(answer.getModifiedBy());
        answerWithCommentResponseDto.setComments(commentToCommentResponeDtos(comments));

        return answerWithCommentResponseDto;
    }




    default List<CommentResponseDto> commentToCommentResponeDtos(List<Comment> comments){
        return comments
                .stream()
                .map(comment -> CommentResponseDto
                        .builder()
                        .commentId(comment.getCommentId())
                        .userId(comment.getUser().getUserId())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());

    }
}
