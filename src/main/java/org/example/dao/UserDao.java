package org.example.dao;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    boolean existsUserByEmail(String email);

    boolean existsUserByPhoneNumber(String phoneNumber);

    User addUser(User user);

    Optional<User> deleteUserById(int id);
}
