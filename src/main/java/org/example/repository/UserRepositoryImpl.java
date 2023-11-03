package org.example.repository;

import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.example.utils.Utils.CREATE_TABLE_STATEMENT;
import static org.example.utils.Utils.EXISTS_BY_EMAIL_STATEMENT;
import static org.example.utils.Utils.EXISTS_BY_PHONE_NUMBER_STATEMENT;
import static org.example.utils.Utils.INSERT_STATEMENT;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        EXISTS_BY_EMAIL_STATEMENT,
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
                PreparedStatement statement = connection.prepareStatement(
                        INSERT_STATEMENT,
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
        try (
                PreparedStatement statement = connection.prepareStatement(
                        EXISTS_BY_PHONE_NUMBER_STATEMENT,
                        RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, phoneNumber);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
