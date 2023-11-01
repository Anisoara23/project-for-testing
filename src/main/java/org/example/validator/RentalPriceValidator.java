package org.example.validator;

import org.example.dto.CarDto;

import java.math.BigDecimal;

public class RentalPriceValidator {

    private static final int LOWER_BOUND_PRICE = 50;

    private static final int UPPER_BOUND_PRICE = 500;

    public static void validateRentalPrice(BigDecimal rentalPrice) {
        if (rentalPrice.doubleValue() < LOWER_BOUND_PRICE) {
            throw new IllegalArgumentException("Rental Price should be equal or more than 50");
        } else if (rentalPrice.doubleValue() > UPPER_BOUND_PRICE) {
            throw new IllegalArgumentException("Rental Price should be equal or less than 500");
        }
    }
}
