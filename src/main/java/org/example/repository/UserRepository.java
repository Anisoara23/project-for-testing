package org.example.repository;

import org.example.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    boolean existsUserByEmail(String email);

    User saveUser(User user);

    boolean deleteUserById(int id);

    boolean existsUserByPhoneNumber(String phoneNumber);
}
