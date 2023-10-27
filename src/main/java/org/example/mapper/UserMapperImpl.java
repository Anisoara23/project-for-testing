package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

public class UserMapperImpl implements UserMapper{

    @Override
    public UserDto userToUserDto(User user) {
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        return new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
    }
}
