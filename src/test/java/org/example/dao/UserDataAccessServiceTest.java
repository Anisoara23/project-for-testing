package org.example.dao;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.MARIA;

@ExtendWith(MockitoExtension.class)
class UserDataAccessServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDataAccessService userDao;

    @Test
    void testGetAllUsers_whenGetAllUsers_thenReturnListOfUsers() {
        when(userRepository.findAllUsers())
                .thenReturn(List.of(JOHN, MARIA));

        List<User> users = userDao.getAllUsers();

        assertTrue(users.containsAll(List.of(JOHN, MARIA)));
        verify(userRepository).findAllUsers();
    }

    @Test
    void testGetAllUsers_whenGetAllUsersFromEmptyRepo_thenReturnEmptyList() {
        when(userRepository.findAllUsers())
                .thenReturn(List.of());

        List<User> users = userDao.getAllUsers();

        assertTrue(users.isEmpty());
        verify(userRepository).findAllUsers();
    }

    @Test
    void testExistsUserByEmail_whenUserByEmailExists_thenReturnTrue() {
        when(userRepository.existsUserByEmail(anyString()))
                .thenReturn(true);

        boolean existsUserByEmail = userDao.existsUserByEmail(JOHN.getEmail());

        assertTrue(existsUserByEmail);
        verify(userRepository).existsUserByEmail(anyString());
    }


    @Test
    void testExistsUserByEmail_whenNoUserWithProvidedEmail_thenReturnFalse() {
        when(userRepository.existsUserByEmail(anyString()))
                .thenReturn(false);

        boolean existsUserByEmail = userDao.existsUserByEmail("unknown");

        assertFalse(existsUserByEmail);
        verify(userRepository).existsUserByEmail(anyString());
    }


    @Test
    void testExistsUserByPhoneNumber_whenUserWithProvidedPhoneExists_thenReturnTrue() {
        when(userRepository.existsUserByPhoneNumber(anyString()))
                .thenReturn(true);

        boolean existsUserByPhoneNumber = userDao.existsUserByPhoneNumber(JOHN.getPhoneNumber());

        assertTrue(existsUserByPhoneNumber);
        verify(userRepository).existsUserByPhoneNumber(anyString());
    }

    @Test
    void testExistsUserByPhoneNumber_whenNoUserWithProvidedPhone_thenReturnFalse() {
        when(userRepository.existsUserByPhoneNumber(anyString()))
                .thenReturn(false);

        boolean existsUserByPhoneNumber = userDao.existsUserByPhoneNumber(JOHN.getPhoneNumber());

        assertFalse(existsUserByPhoneNumber);
        verify(userRepository).existsUserByPhoneNumber(anyString());
    }

    @Test
    void testAddUser_whenAddNewUser_thenAddedUserIsReturned() {
        when(userRepository.saveUser(any(User.class)))
                .thenReturn(JOHN);

        User user = userDao.addUser(JOHN);

        assertEquals(JOHN, user);
        verify(userRepository).saveUser(any(User.class));
    }

    @Test
    void testDeleteUserById_whenDeleteExistingUser_thenReturnTrue() {
        when(userRepository.deleteUserById(anyInt()))
                .thenReturn(true);

        boolean isUserDeleted = userDao.deleteUserById(JOHN.getId());

        assertTrue(isUserDeleted);
        verify(userRepository).deleteUserById(anyInt());
    }

    @Test
    void testDeleteUserById_whenDeleteNonExistingUser_thenReturnFalse() {
        when(userRepository.deleteUserById(anyInt()))
                .thenReturn(false);

        boolean isUserDeleted = userDao.deleteUserById(JOHN.getId());

        assertFalse(isUserDeleted);
        verify(userRepository).deleteUserById(anyInt());
    }
}