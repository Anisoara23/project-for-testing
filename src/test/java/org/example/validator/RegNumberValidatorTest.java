package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.INVALID_REG_NUMBER;
import static utils.TestUtils.NULL_REG_NUMBER;

class RegNumberValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"ABC123", "abc123"})
    void testIsRegNumberValid_whenProvideCorrectRegNumber_thenReturnTrue(String regNumber) {
        assertTrue(RegNumberValidator.isRegNumberValid(regNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab12", "123abc", "ABC", "123"})
    void testIsRegNumberValid_whenProvideIncorrectRegNumber_thenThrow(String regNumber) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> RegNumberValidator.isRegNumberValid(regNumber));
        assertEquals(INVALID_REG_NUMBER, illegalArgumentException.getMessage());
    }

    @Test
    void testIsRegNumberValid_whenProvideNull_thenThrow() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> RegNumberValidator.isRegNumberValid(null));
        assertEquals(NULL_REG_NUMBER, nullPointerException.getMessage());
    }
}