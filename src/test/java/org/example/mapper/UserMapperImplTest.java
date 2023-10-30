package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.JOHN_DTO;

class UserMapperImplTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void testUserToUserDto() {
        UserDto mappedUser = userMapper.userToUserDto(JOHN);
        assertEquals(JOHN_DTO, mappedUser);
    }

    @Test
    void testUserDtoToUser() {
        User mappedUser = userMapper.userDtoToUser(JOHN_DTO);
        assertEquals(JOHN, mappedUser);
    }
}