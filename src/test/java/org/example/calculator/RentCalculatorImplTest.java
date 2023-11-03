package org.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtils.BOOKING;

class RentCalculatorImplTest {

    private RentCalculator rentCalculator;

    @BeforeEach
    void setUp() {
        rentCalculator = new RentCalculatorImpl();
    }

    @Test
    void testGetTotalRentalPrice() {
        double actualRentalPrice = rentCalculator.getTotalRentalPrice(BOOKING);
        int expectedRentalPrice = 400;

        assertEquals(expectedRentalPrice, actualRentalPrice);
    }
}