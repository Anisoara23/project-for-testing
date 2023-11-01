package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegNumberValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "ABC123",
            "abc123"
    })
    void testIsRegNumberValid_whenProvideCorrectRegNumber_thenReturnTrue(String regNumber) {
        assertTrue(RegNumberValidator.isRegNumberValid(regNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ab12",
            "123abc",
            "ABC",
            "123"
    })
    void testIsRegNumberValid_whenProvideIncorrectRegNumber_thenReturnFalse(String regNumber) {
        assertFalse(RegNumberValidator.isRegNumberValid(regNumber));
    }

    @Test
    void testIsRegNumberValid_whenProvideNull_thenReturnFalse() {
        assertFalse(RegNumberValidator.isRegNumberValid(null));
    }
}