package org.example.repository;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();

    boolean existsUserByEmail(String email);

    User addUser(User user);

    Optional<User> deleteUserById(int id);

    boolean existsUserByPhoneNumber(String phoneNumber);
}
