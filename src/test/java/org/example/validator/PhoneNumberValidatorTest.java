package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "098-098-098",
            "098765432"
    })
    void testIsPhoneNumberValid_whenProvideCorrectPhoneNumber_thenReturnTrue(String phoneNumber) {
        assertTrue(PhoneNumberValidator.isPhoneNumberValid(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0987654321",
            "0982-0981-0981",
            "987-986-876",
            "273546278",
            "test",
            "098--765-765",
            "09-98-87",
            "09876543"
    })
    void testIsPhoneNumberValid_whenProvideIncorrectPhoneNumber_thenThrow(String phoneNumber) {
        assertFalse(PhoneNumberValidator.isPhoneNumberValid(phoneNumber));
    }

    @Test
    void testIsPhoneNumberValid_whenProvideNull_thenThrow() {
        assertFalse(PhoneNumberValidator.isPhoneNumberValid(null));
    }
}