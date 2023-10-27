package org.example.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.EMAIL_IS_NOT_VALID;
import static utils.TestUtils.EMAIL_IS_NULL;

class EmailValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"test@mail.com", "12345@mail.net", "test123@mail.com", "Test@mail.com", "TEST@MAIL.COM", "Test123@amil.com"})
    void testIsValidEmail_whenProvideCorrectEmail_thenReturnTrue(String email) {
        assertTrue(EmailValidator.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "test", "test.com", "test@mail..com", "test@mail.", "test@mail"})
    void testIsValidEmail_whenProvideIncorrectEmail_thenThrow(String email) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> EmailValidator.isValidEmail(email));
        assertEquals(EMAIL_IS_NOT_VALID, illegalArgumentException.getMessage());
    }

    @Test
    void testIsValidEmail_whenProvideNull_thenThrow() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> EmailValidator.isValidEmail(null));
        assertEquals(EMAIL_IS_NULL, nullPointerException.getMessage());
    }
}