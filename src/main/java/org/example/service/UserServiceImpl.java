package org.example.service;

import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.validator.EmailValidator;
import org.example.validator.PhoneNumberValidator;

import java.util.List;

import static org.example.validator.AgeValidator.validateAge;

public class UserServiceImpl implements UserService {

    public static final int ACCEPTED_USER_AGE = 18;

    private final UserDao userDao;

    private final UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers().stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userDao.existsUserByEmail(email);
    }

    @Override
    public UserDto addUser(UserDto userDto) {

        validateUserDetails(userDto);

        User user = userMapper.userDtoToUser(userDto);
        User addedUser = userDao.addUser(user);
        return userMapper.userToUserDto(addedUser);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        User mappedUserToDelete = userMapper.userDtoToUser(userDto);
        User userToDelete = userDao.getAllUsers().stream()
                .filter(user -> user.equals(mappedUserToDelete))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No user with provided details!"));

        userDao.deleteUserById(userToDelete.getId());
    }

    private void validateUserDetails(UserDto userDto) {
        validateEmail(userDto);
        validatePhoneNumber(userDto);
        validateAge(userDto.getAge(), ACCEPTED_USER_AGE);
    }

    private void validatePhoneNumber(UserDto userDto) {
        if (!PhoneNumberValidator.isPhoneNumberValid(userDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number format!");
        }

        if (userDao.existsUserByPhoneNumber(userDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number is already taken by other user!");
        }
    }

    private void validateEmail(UserDto userDto) {
        if (!EmailValidator.isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email is not valid!");
        }

        if (userDao.existsUserByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email is already taken by other user!");
        }
    }
}
