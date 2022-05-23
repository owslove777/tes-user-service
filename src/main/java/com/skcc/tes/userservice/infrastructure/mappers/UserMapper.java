package com.skcc.tes.userservice.infrastructure.mappers;

import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.infrastructure.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToStarUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> userList);

    List<User> userDtoListToUserList(List<UserDto> userDtoList);
}
