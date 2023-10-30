package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.TestUtils.BOOKING;

class BookingTest {

    @Test
    void testGetRentalPrice() {
        double totalRentalPrice = BOOKING.getTotalRentalPrice();
        int expectedTotalRentalPrice = 400;

        assertEquals(expectedTotalRentalPrice, totalRentalPrice);
    }
}