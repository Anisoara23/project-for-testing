package org.example.validator;

import org.example.dto.CarDto;

import java.math.BigDecimal;

public class RentalPriceValidator {

    public static void validateRentalPrice(BigDecimal rentalPrice) {
        if (rentalPrice.doubleValue() < 50) {
            throw new IllegalArgumentException("Rental Price should be equal or more than 50");
        } else if (rentalPrice.doubleValue() > 500) {
            throw new IllegalArgumentException("Rental Price should be equal or less than 500");
        }
    }

}
