package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.UserService;

public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return userService.addUser(userDto);
    }
}
