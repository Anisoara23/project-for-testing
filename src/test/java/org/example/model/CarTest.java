package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.MERCEDES;

class CarTest {

    @BeforeEach
    void setUp() {
        if (MERCEDES.isBooked()) {
            MERCEDES.cancelBooking();
        }
    }

    @Test
    void testBook_whenBookAnUnusedCar_thenCarIsBooked() {
        MERCEDES.book();

        assertTrue(MERCEDES.isBooked());
    }

    @Test
    void testCancelBooking_whenCancelExistingBooking_thenCarBookingIsCanceled() {
        MERCEDES.book();
        MERCEDES.cancelBooking();

        assertFalse(MERCEDES.isBooked());
    }
}