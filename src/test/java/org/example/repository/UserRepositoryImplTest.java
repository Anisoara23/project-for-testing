package org.example.repository;

import org.example.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.MARIA;
import static utils.TestUtils.PASSWORD;
import static utils.TestUtils.URL;
import static utils.TestUtils.USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryImplTest {

    private static UserRepositoryImpl userRepository;

    private static User savedUser;

    @BeforeAll
    static void beforeAll() throws SQLException {
        Connection connection = getConnection(URL, USER, PASSWORD);
        userRepository = new UserRepositoryImpl(connection);
        userRepository.createTable();
        savedUser = userRepository.saveUser(MARIA);
    }

    @Order(1)
    @Test
    void testExistsUserByEmail_whenTheUserDoesNotExist() {
        boolean existsUserByEmail = userRepository.existsUserByEmail(JOHN.getEmail());

        assertFalse(existsUserByEmail);
    }

    @Order(2)
    @Test
    void testExistsUserByPhoneNumber_whenTheUserDoesNotExist() {
        boolean existsUserByPhoneNumber = userRepository.existsUserByPhoneNumber(JOHN.getPhoneNumber());

        assertFalse(existsUserByPhoneNumber);
    }

    @Order(3)
    @Test
    void testAddUser() {
        User savedUser = userRepository.saveUser(JOHN);

        assertTrue(savedUser.getId() != 0);
        assertEquals(JOHN, savedUser);
    }

    @Order(4)
    @Test
    void testExistsUserByEmail_whenTheUserExists() {
        boolean existsUserByEmail = userRepository.existsUserByEmail(JOHN.getEmail());

        assertTrue(existsUserByEmail);
    }

    @Order(5)
    @Test
    void testExistsUserByPhoneNumber_whenTheUserExists() {
        boolean existsUserByPhoneNumber = userRepository.existsUserByPhoneNumber(JOHN.getPhoneNumber());

        assertTrue(existsUserByPhoneNumber);
    }

    @Test
    @Order(6)
    void testSelectAllUsers() {
        List<User> users = userRepository.findAllUsers();

        assertTrue(users.contains(MARIA));
    }

    @Test
    @Order(7)
    void testDeleteUserById_whenDeleteUserByUnknownId_thenReturnEmpty() {
        boolean isUserDeleted = userRepository.deleteUserById(10);

        assertFalse(isUserDeleted);
    }

    @Test
    @Order(8)
    void testDeleteUserById_whenDeleteUserByKnownId_thenReturnDeletedUser() {
        boolean isUserDeleted = userRepository.deleteUserById(savedUser.getId());

        assertTrue(isUserDeleted);
    }
}