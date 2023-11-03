package org.example.dataimporter;

import org.example.controller.UserController;
import org.example.dto.UserDto;
import org.example.reader.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static utils.TestUtils.JOHN_DTO;
import static utils.TestUtils.LIST_OF_JOHN_CREDENTIALS;

@ExtendWith(MockitoExtension.class)
class UserDataImporterTest {

    @Mock
    private Reader reader;

    @Mock
    private UserController userController;

    @InjectMocks
    private UserDataImporter userDataImporter;

    @Test
    void testImportUsers() {
        when(reader.readData())
                .thenReturn(List.of(LIST_OF_JOHN_CREDENTIALS));
        when(userController.addUser(any(UserDto.class)))
                .thenReturn(JOHN_DTO);

        assertDoesNotThrow(() -> userDataImporter.importUsers());
    }
}