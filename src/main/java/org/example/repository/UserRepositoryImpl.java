package org.example.repository;

import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.example.utils.Utils.PASSWORD;
import static org.example.utils.Utils.URL;
import static org.example.utils.Utils.USER;

public class UserRepositoryImpl implements UserRepository {


    private final String insertStatement = "INSERT INTO \"user\"(" +
            "first_name, " +
            "last_name, " +
            "age, " +
            "email, " +
            "phone_number" +
            ") " +
            "VALUES(?, ?, ?, ?, ?);";

    private final String existsByEmailStatement = "SELECT FROM \"user\" " +
            "WHERE email = ?;";

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        try (
                Connection connection = getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        existsByEmailStatement,
                        RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, email);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User saveUser(User user) {
        try (
                Connection connection = getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        insertStatement,
                        RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> deleteUserById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean existsUserByPhoneNumber(String phoneNumber) {
        return false;
    }
}
