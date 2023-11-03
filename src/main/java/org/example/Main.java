package org.example;

import org.example.controller.UserController;
import org.example.controller.UserControllerImpl;
import org.example.dao.UserDao;
import org.example.dao.UserDataAccessService;
import org.example.dataimporter.UserDataImporter;
import org.example.mapper.UserMapper;
import org.example.mapper.UserMapperImpl;
import org.example.reader.CsvReader;
import org.example.reader.Reader;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.example.utils.Utils.PASSWORD;
import static org.example.utils.Utils.URL;
import static org.example.utils.Utils.USER;
import static org.example.utils.Utils.USERS_CSV;

public class Main {

    public static void main(String[] args) throws SQLException {
        Reader reader = new CsvReader(USERS_CSV);
        Connection connection = getConnection(URL, USER, PASSWORD);
        UserRepository userRepository = new UserRepositoryImpl(connection);
        UserDao userDao = new UserDataAccessService(userRepository);
        UserMapper userMapper = new UserMapperImpl();
        UserService userService = new UserServiceImpl(
                userDao,
                userMapper
        );
        UserController userController = new UserControllerImpl(userService);
        UserDataImporter dataImporter = new UserDataImporter(
                reader,
                userController
        );

        dataImporter.importUsers();
    }
}