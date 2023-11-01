package org.example.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.LOWER_RENTAL_PRICE;
import static utils.TestUtils.SHOULD_BE_EQUAL_OR_LESS_THAN_500;
import static utils.TestUtils.SHOULD_BE_EQUAL_OR_MORE_THAN_50;
import static utils.TestUtils.UPPER_RENTAL_PRICE;
import static utils.TestUtils.VALID_RENTAL_PRICE;

class RentalPriceValidatorTest {

    @Test
    void testValidateRentalPrice_whenValidRentalPrice_thenDoNothing() {
        assertDoesNotThrow(
                () -> RentalPriceValidator.validateRentalPrice(VALID_RENTAL_PRICE)
        );
    }

    @Test
    void testValidateRentalPrice_whenLowerRentalPrice_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> RentalPriceValidator.validateRentalPrice(LOWER_RENTAL_PRICE)
        );

        assertEquals(SHOULD_BE_EQUAL_OR_MORE_THAN_50, illegalArgumentException.getMessage());
    }

    @Test
    void testValidateRentalPrice_whenUpperRentalPrice_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> RentalPriceValidator.validateRentalPrice(UPPER_RENTAL_PRICE)
        );

        assertEquals(SHOULD_BE_EQUAL_OR_LESS_THAN_500, illegalArgumentException.getMessage());
    }
}