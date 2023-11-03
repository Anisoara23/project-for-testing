package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    boolean existsUserByEmail(String email);

    boolean existsUserByPhoneNumber(String phoneNumber);

    User addUser(User user);

    boolean deleteUserById(int id);
}
