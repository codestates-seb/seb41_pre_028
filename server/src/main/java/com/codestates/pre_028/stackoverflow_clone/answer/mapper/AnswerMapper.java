package com.codestates.pre_028.stackoverflow_clone.answer.mapper;
//추후 주석제거
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import org.mapstruct.Mapper;
//import com.codestates.pre_028.stackoverflow_clone.user.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.question.entity.Qusetion;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto){
        Answer answer = new Answer();
//        Question question = new Question();
//        User user = new User();
//
//        question.setQuestionId(answerPostDto.getQuestionId());
//        user.setUserId(answerPostDto.getUserId());
//
//        answer.setQuestion(question);
//        answer.setUser(user);
        answer.setContent(answerPostDto.getContent());

        return answer;
    }

    default AnswerDto.Response answerToAnswerResponseDto(Answer answer){
        AnswerDto.Response answerDtoResponse = new AnswerDto.Response();

        answerDtoResponse.setAnswerId(answer.getAnswerId());
//        answerDtoResponse.setQuestion(answer.getQuestion());
//        answerDtoResponse.setUser(answer.getUser());
        answerDtoResponse.setContent(answer.getContent());
        answerDtoResponse.setAnswerStatus(answer.getAnswerStatus());

//        answerDtoResponse.setVote(answer.getVoteAnswers().size());

        //audit?
//        answerDtoResponse.setCreatedAt(answer.getCreatedAt());
//        answerDtoResponse.setModifiedAt(answer.getModifiedAt());
//        answerDtoResponse.setCreatedBy(answer.getCreatedBy());
//        answerDtoResponse.setModifiedBy(answer.getModifiedBy());

        return answerDtoResponse;
    }
}
