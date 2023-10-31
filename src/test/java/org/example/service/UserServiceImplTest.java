package org.example.service;

import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.validator.AgeValidator;
import org.example.validator.EmailValidator;
import org.example.validator.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.AGE_LESS_THAN_ACCEPTED;
import static utils.TestUtils.EMAIL_IS_NOT_VALID;
import static utils.TestUtils.EMAIL_IS_TAKEN;
import static utils.TestUtils.INVALID_PHONE_NUMBER_FORMAT;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.JOHN_DTO;
import static utils.TestUtils.MARIA;
import static utils.TestUtils.NO_USER_WITH_PROVIDED_DETAILS;
import static utils.TestUtils.PHONE_NUMBER_IS_TAKEN;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUsers_whenGetAllUsers_thenReturnAllUsers() {
        when(userDao.getAllUsers()).thenReturn(List.of(JOHN));
        when(userMapper.userToUserDto(any(User.class))).thenReturn(JOHN_DTO);

        List<UserDto> users = userService.getAllUsers();

        assertTrue(users.contains(JOHN_DTO));
        verify(userDao).getAllUsers();
        verify(userMapper).userToUserDto(any(User.class));
    }

    @Test
    void testExistsUserByEmail_whenUserByEmailExists_thenReturnTrue() {
        when(userDao.existsUserByEmail(anyString())).thenReturn(true);

        boolean existsUserByEmail = userService.existsUserByEmail(JOHN.getEmail());

        assertTrue(existsUserByEmail);
        verify(userDao).existsUserByEmail(anyString());
    }


    @Test
    void testExistsUserByEmail_whenNoUserWithProvidedEmail_thenReturnFalse() {
        when(userDao.existsUserByEmail(anyString())).thenReturn(false);

        boolean existsUserByEmail = userService.existsUserByEmail("unknown");

        assertFalse(existsUserByEmail);
        verify(userDao).existsUserByEmail(anyString());
    }

    @Test
    void testAddNewUser_whenAddValidUser_thenAddedUserIsReturned() {
        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(JOHN);
        when(userDao.addUser(any(User.class))).thenReturn(JOHN);
        when(userMapper.userToUserDto(any(User.class))).thenReturn(JOHN_DTO);

        UserDto userDto = userService.addUser(JOHN_DTO);

        assertEquals(JOHN_DTO, userDto);
        verify(userMapper).userDtoToUser(any(UserDto.class));
        verify(userDao).addUser(any(User.class));
        verify(userMapper).userToUserDto(any(User.class));
    }

    @Test
    void testAddNewUser_whenAddUserWithInvalidEmail_thenThrow() {
        try (MockedStatic<EmailValidator> emailValidatorMocked = mockStatic(EmailValidator.class)) {
            emailValidatorMocked.when(() -> EmailValidator.isValidEmail(anyString())).thenReturn(false);

            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                    () -> userService.addUser(JOHN_DTO));

            assertEquals(EMAIL_IS_NOT_VALID, illegalArgumentException.getMessage());
        }
    }

    @Test
    void testAddNewUser_whenAddUserWithAlreadyUsedEmail_thenThrow() {
        when(userDao.existsUserByEmail(anyString())).thenReturn(true);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(JOHN_DTO));

        assertEquals(EMAIL_IS_TAKEN, illegalArgumentException.getMessage());
        verify(userDao).existsUserByEmail(anyString());
    }

    @Test
    void testAddNewUser_whenAddUserWithInvalidPhoneNumber_thenThrow() {
        try (MockedStatic<PhoneNumberValidator> phoneNumberValidatorMocked = mockStatic(PhoneNumberValidator.class)) {
            phoneNumberValidatorMocked.when(() -> PhoneNumberValidator.isPhoneNumberValid(anyString())).thenReturn(false);

            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                    () -> userService.addUser(JOHN_DTO));

            assertEquals(INVALID_PHONE_NUMBER_FORMAT, illegalArgumentException.getMessage());
        }
    }

    @Test
    void testAddNewUser_whenAddUserWithAlreadyUsedPhoneNumber_thenThrow() {
        when(userDao.existsUserByPhoneNumber(anyString())).thenReturn(true);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(JOHN_DTO));

        assertEquals(PHONE_NUMBER_IS_TAKEN, illegalArgumentException.getMessage());
        verify(userDao).existsUserByEmail(anyString());
    }

    @Test
    void testAddNewUser_whenAddUserWithAgeLessThanEighteen_thenThrow() {
        try (MockedStatic<AgeValidator> ageValidatorMocked = mockStatic(AgeValidator.class)) {
            ageValidatorMocked.when(() -> AgeValidator.validateAge(anyInt(), anyInt()))
                    .thenThrow(new IllegalArgumentException(AGE_LESS_THAN_ACCEPTED));

            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                    () -> userService.addUser(JOHN_DTO));

            assertEquals(AGE_LESS_THAN_ACCEPTED, illegalArgumentException.getMessage());
        }
    }

    @Test
    void testDeleteUserById_whenDeleteExistingUser_thenDoNothing() {
        when(userDao.getAllUsers()).thenReturn(List.of(JOHN, MARIA));

        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(JOHN);
        when(userDao.deleteUserById(anyInt())).thenReturn(Optional.of(JOHN));

        assertDoesNotThrow(() -> userService.deleteUser(JOHN_DTO));
        verify(userDao).deleteUserById(anyInt());
    }

    @Test
    void testDeleteUserById_whenDeleteUnknownUser_thenThrow() {
        when(userDao.getAllUsers()).thenReturn(List.of(MARIA));

        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(JOHN);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUser(JOHN_DTO));
        assertEquals(NO_USER_WITH_PROVIDED_DETAILS, illegalArgumentException.getMessage());

        verify(userDao, times(0)).deleteUserById(anyInt());
    }
}