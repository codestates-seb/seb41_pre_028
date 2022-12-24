package com.codestates.pre_028.stackoverflow_clone.Question.mapper;

import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto);
    QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question);
}
