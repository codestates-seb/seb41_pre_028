package com.codestates.pre_028.stackoverflow_clone.User.mapper;

import com.codestates.pre_028.stackoverflow_clone.Auth.Dto.LoginDto;
import com.codestates.pre_028.stackoverflow_clone.User.Dto.UserDto;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostToUser(UserDto.Post requestBody);

    User userPatchToUser(UserDto.Patch requestBody);

    User loginUserPostToUser(LoginDto requestBody);

    UserDto.Response userToUserResponse(User requestBody);

    List<UserDto.Response> usersToUserResponse(List<User> users);
}
