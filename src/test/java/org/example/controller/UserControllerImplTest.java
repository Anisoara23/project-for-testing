package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.JOHN_DTO;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl userController;

    @Test
    void testAddUser() {
        when(userService.addUser(any(UserDto.class))).thenReturn(JOHN_DTO);

        UserDto addedUser = userController.addUser(JOHN_DTO);

        assertEquals(JOHN_DTO, addedUser);
        verify(userService).addUser(any(UserDto.class));
    }
}