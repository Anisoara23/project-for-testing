package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        return user != null ? new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                user.getPhoneNumber()
        ) : null;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        return userDto != null ? new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        ) : null;
    }
}
