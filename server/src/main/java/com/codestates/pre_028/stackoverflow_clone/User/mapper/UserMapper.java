package com.codestates.pre_028.stackoverflow_clone.User.mapper;

import com.codestates.pre_028.stackoverflow_clone.Auth.Dto.LoginDto;
import com.codestates.pre_028.stackoverflow_clone.Question.Dto.QuestionTitleDto;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserWithAnswerAndQuestionResponseDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.dto.AnswerResponseDto;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostToUser(UserDto.Post requestBody);

    User userPatchToUser(UserDto.Patch requestBody);

    User loginUserPostToUser(LoginDto requestBody);

    UserDto.Response userToUserResponse(User requestBody);

    default UserWithAnswerAndQuestionResponseDto userToUserResponsePage(User user){
        List<Answer> answers = user.getAnswers();
        List<Question> questions = user.getQuestions();
        UserWithAnswerAndQuestionResponseDto userResponseDto = new UserWithAnswerAndQuestionResponseDto();

        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setReputation(user.getReputation());
        userResponseDto.setAnswers(answersToUser(answers));
        userResponseDto.setQuestions(questionsToUser(questions));

        return userResponseDto;

    }

    List<UserDto.Response> usersToUserResponse(List<User> users);

    default List<QuestionTitleDto> questionsToUser(List<Question> questions){
        return questions
                .stream()
                .map(question -> QuestionTitleDto
                        .builder()
                        .questionId(question.getQuestionId())
                        .title(question.getTitle())
                        .build())
                .collect(Collectors.toList());

    }

    default List<AnswerResponseDto> answersToUser(List<Answer> answers){
        return answers
                .stream()
                .map(answer -> AnswerResponseDto
                        .builder()
                        .answerId(answer.getAnswerId())
                        .questionId(answer.getQuestion().getQuestionId())
                        .userId(answer.getUser().getUserId())
                        .vote(answer.getVoteAnswer().getAnswer().getVoteAnswer())
                        .content(answer.getContent())
                        .build())
                .collect(Collectors.toList());

    }
}




