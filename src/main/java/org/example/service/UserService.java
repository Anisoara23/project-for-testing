package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    boolean existsUserByEmail(String email);

    UserDto addUser(UserDto userDto);

    void deleteUser(UserDto userDto);
}
