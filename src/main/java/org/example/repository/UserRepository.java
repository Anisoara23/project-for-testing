package org.example.repository;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAllUsers();

    boolean existsUserByEmail(String email);

    User saveUser(User user);

    Optional<User> deleteUserById(int id);

    boolean existsUserByPhoneNumber(String phoneNumber);
}
