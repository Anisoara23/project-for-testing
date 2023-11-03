package org.example.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtils.LIST_OF_JOHN_CREDENTIALS;
import static utils.TestUtils.USERS_CSV;

class CsvReaderTest {

    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CsvReader(USERS_CSV);
    }

    @Test
    void testReadData() {
        List<List<String>> expectedData = List.of(LIST_OF_JOHN_CREDENTIALS);

        List<List<String>> actualReadData = csvReader.readData();

        assertEquals(expectedData, actualReadData);
    }
}