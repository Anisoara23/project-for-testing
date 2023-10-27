package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
