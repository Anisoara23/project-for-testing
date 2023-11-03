package org.example.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtils.USERS_CSV;

class CsvReaderTest {

    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CsvReader(USERS_CSV);
    }

    @Test
    void testReadData() {
        List<List<String>> readData = csvReader.readData();
        List<List<String>> expectedData = List.of(
                List.of("firstName", "lastName", "age", "email", "phoneNumber"),
                List.of("John", "Smith", "23", "johny@mail.com", "098765432")
        );

        assertEquals(expectedData, readData);
    }
}