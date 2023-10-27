package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.INVALID_PHONE_NUMBER_FORMAT;
import static utils.TestUtils.PHONE_NUMBER_IS_NULL;

class PhoneNumberValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"098-098-098", "098765432"})
    void testIsPhoneNumberValid_whenProvideCorrectPhoneNumber_thenReturnTrue(String phoneNumber) {
        assertTrue(PhoneNumberValidator.isPhoneNumberValid(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0987654321", "0982-0981-0981", "987-986-876", "273546278", "test", "098--765-765", "09-98-87", "09876543"})
    void testIsPhoneNumberValid_whenProvideIncorrectPhoneNumber_thenThrow(String phoneNumber) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> PhoneNumberValidator.isPhoneNumberValid(phoneNumber));
        assertEquals(INVALID_PHONE_NUMBER_FORMAT, illegalArgumentException.getMessage());
    }

    @Test
    void testIsPhoneNumberValid_whenProvideNull_thenThrow() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> PhoneNumberValidator.isPhoneNumberValid(null));
        assertEquals(PHONE_NUMBER_IS_NULL, nullPointerException.getMessage());
    }
}