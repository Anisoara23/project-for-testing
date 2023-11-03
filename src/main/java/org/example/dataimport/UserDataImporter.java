package org.example.dataimport;

import org.example.controller.UserController;
import org.example.dto.UserDto;
import org.example.reader.Reader;

import java.util.List;

public class UserDataImporter {

    private final Reader reader;

    private final UserController userController;

    public UserDataImporter(Reader reader, UserController userController) {
        this.reader = reader;
        this.userController = userController;
    }

    public void importUsers() {
        List<List<String>> readData = reader.readData();

        for (List<String> next : readData) {
            UserDto userDto = new UserDto(
                    next.get(0),
                    next.get(1),
                    Integer.parseInt(next.get(2)),
                    next.get(3),
                    next.get(4)
            );

            userController.addUser(userDto);
        }
    }
}
