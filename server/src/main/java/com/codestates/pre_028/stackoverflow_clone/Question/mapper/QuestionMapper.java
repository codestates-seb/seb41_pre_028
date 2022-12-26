package com.codestates.pre_028.stackoverflow_clone.Question.mapper;

import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionWithAnswerDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerResponseDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    default Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto){
        Question question = new Question();
        User user = new User();
        user.setUserId(questionPostDto.getUserId());

        question.setUser(user);
        question.setTitle(questionPostDto.getTitle());
        question.setContent(questionPostDto.getContent());
        question.setTag(questionPostDto.getTag());
        return question;

    };
    Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto);
    default QuestionWithAnswerDto questionToQuestionResponseDto(Question question){
        List<Comment> comments = question.getComments();
        List<Answer> answers = question.getAnswerList();
        QuestionWithAnswerDto questionResponseDto = new QuestionWithAnswerDto();

        questionResponseDto.setQuestionId(question.getQuestionId());
        questionResponseDto.setContent(question.getContent());
        questionResponseDto.setUser(question.getUser());

        //메타데이타
        questionResponseDto.setCreatedAt(question.getCreatedAt());
        questionResponseDto.setCreatedBy(question.getCreatedBy());
        questionResponseDto.setModifiedAt(question.getModifiedAt());
        questionResponseDto.setModifiedBy(question.getModifiedBy());

        //답변
        questionResponseDto.setAnswerResponseDtos(answerToAnswerWithQuestionResponseDtos(answers));

        //댓글
        questionResponseDto.setComments(commentToCommentWithQuestionResponeDtos(comments));

        return questionResponseDto;
    }

    //코멘트 리스트화
    default List<CommentResponseDto> commentToCommentWithQuestionResponeDtos(List<Comment> comments){
        return comments
                .stream()
                .map(comment -> CommentResponseDto
                        .builder()
                        .userId(comment.getUser().getUserId())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());

    }
    //앤서 리스트화
    default List<AnswerResponseDto> answerToAnswerWithQuestionResponseDtos(List<Answer> answers){
        return answers
                .stream()
                .map(answer -> AnswerResponseDto
                        .builder()
                        .userId(answer.getUser().getUserId())
                        .nickname(answer.getUser().getNickname())
                        .content(answer.getContent())
                        .build())
                .collect(Collectors.toList());

    }
}
