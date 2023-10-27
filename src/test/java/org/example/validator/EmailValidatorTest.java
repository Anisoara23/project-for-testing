package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"test@mail.com", "12345@mail.net", "test123@mail.com", "Test@mail.com", "TEST@MAIL.COM", "Test123@amil.com"})
    void testIsValidEmail_whenProvideCorrectEmail_thenReturnTrue(String email) {
        assertTrue(EmailValidator.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "test", "test.com", "test@mail..com", "test@mail.", "test@mail"})
    void testIsValidEmail_whenProvideIncorrectEmail_thenThrow(String email) {
        assertFalse(EmailValidator.isValidEmail(email));
    }

    @Test
    void testIsValidEmail_whenProvideNull_thenThrow() {
        assertFalse(EmailValidator.isValidEmail(null));
    }
}