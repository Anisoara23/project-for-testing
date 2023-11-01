package org.example.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.AGE_LESS_THAN_ACCEPTED;
import static utils.TestUtils.PROVIDED_INVALID_AGE;
import static utils.TestUtils.PROVIDED_VALID_AGE;
import static utils.TestUtils.VALID_AGE;

class AgeValidatorTest {

    @Test
    void testValidateAge_whenAgeValid_thenDoNothing() {
        assertDoesNotThrow(() -> AgeValidator
                .validateAge(PROVIDED_VALID_AGE, VALID_AGE));
    }

    @Test
    void testValidateAge_whenAgeIsNotValid_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> AgeValidator.validateAge(PROVIDED_INVALID_AGE, VALID_AGE)
        );

        assertEquals(AGE_LESS_THAN_ACCEPTED, illegalArgumentException.getMessage());
    }
}