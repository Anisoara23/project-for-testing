package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.TestUtils.MARIA;
import static utils.TestUtils.MARIA_DTO;

class UserMapperImplTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void testUserToUserDto_whenUserIsNotNull() {
        UserDto mappedUser = userMapper.userToUserDto(MARIA);
        assertEquals(MARIA_DTO, mappedUser);
    }

    @Test
    void testUserToUserDto_whenUserIsNull() {
        UserDto mappedUser = userMapper.userToUserDto(null);
        assertNull(mappedUser);
    }

    @Test
    void testUserDtoToUse_whenUserDtoIsNotNull() {
        User mappedUser = userMapper.userDtoToUser(MARIA_DTO);
        assertEquals(MARIA, mappedUser);
    }

    @Test
    void testUserDtoToUser_whenUserDtoIsNull() {
        User mappedUser = userMapper.userDtoToUser(null);
        assertNull(mappedUser);
    }
}