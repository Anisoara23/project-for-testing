package org.example.dao;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
        when(userRepository.getAllUsers()).thenReturn(List.of(JOHN, MARIA));

        List<User> users = userDao.getAllUsers();

        assertTrue(users.containsAll(List.of(JOHN, MARIA)));
        verify(userRepository).getAllUsers();
    }

    @Test
    void testGetAllUsers_whenGetAllUsersFromEmptyRepo_thenReturnEmptyList() {
        when(userRepository.getAllUsers()).thenReturn(List.of());

        List<User> users = userDao.getAllUsers();

        assertTrue(users.isEmpty());
        verify(userRepository).getAllUsers();
    }

    @Test
    void testExistsUserByEmail_whenUserByEmailExists_thenReturnTrue() {
        when(userRepository.existsUserByEmail(anyString())).thenReturn(true);

        boolean existsUserByEmail = userDao.existsUserByEmail(JOHN.getEmail());

        assertTrue(existsUserByEmail);
        verify(userRepository).existsUserByEmail(anyString());
    }


    @Test
    void testExistsUserByEmail_whenNoUserWithProvidedEmail_thenReturnFalse() {
        when(userRepository.existsUserByEmail(anyString())).thenReturn(false);

        boolean existsUserByEmail = userDao.existsUserByEmail("unknown");

        assertFalse(existsUserByEmail);
        verify(userRepository).existsUserByEmail(anyString());
    }

    @Test
    void testAddUser_whenAddNewUser_thenAddedUserIsReturned() {
        when(userRepository.addUser(any(User.class))).thenReturn(JOHN);

        User user = userDao.addUser(JOHN);

        assertEquals(JOHN, user);
        verify(userRepository).addUser(any(User.class));
    }

    @Test
    void testDeleteUserById_whenDeleteExistingUser_thenReturnDeletedUser() {
        when(userRepository.deleteUserById(anyInt())).thenReturn(Optional.of(JOHN));

        Optional<User> user = userDao.deleteUserById(JOHN.getId());

        assertEquals(JOHN, user.get());
        verify(userRepository).deleteUserById(anyInt());
    }

    @Test
    void testDeleteUserById_whenDeleteNonExistingUser_thenReturnNull() {
        when(userRepository.deleteUserById(anyInt())).thenReturn(Optional.empty());

        Optional<User> user = userDao.deleteUserById(JOHN.getId());

        assertTrue(user.isEmpty());
        verify(userRepository).deleteUserById(anyInt());
    }
}