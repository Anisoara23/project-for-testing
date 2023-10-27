package org.example.dao;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserDataAccessService implements UserDao{

    private final UserRepository userRepository;

    public UserDataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByPhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public Optional<User> deleteUserById(int id) {
        return userRepository.deleteUserById(id);
    }
}
