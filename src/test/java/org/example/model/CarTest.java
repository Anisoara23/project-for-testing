package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.BOOKING;
import static utils.TestUtils.CAR_IS_ALREADY_BOOKED;
import static utils.TestUtils.MERCEDES;
import static utils.TestUtils.NO_BOOKING_FOR_CAR;

class CarTest {

    @BeforeEach
    void setUp() {
        if (MERCEDES.isBooked()) {
            MERCEDES.cancelBooking();
        }
    }

    @Test
    void testBook_whenBookAnUnusedCar_thenCarIsBooked() {
        MERCEDES.book(BOOKING);

        assertTrue(MERCEDES.isBooked());
    }

    @Test
    void testBook_whenBookAnUsedCar_thenThrow() {
        MERCEDES.book(BOOKING);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> MERCEDES.book(BOOKING));
        assertEquals(CAR_IS_ALREADY_BOOKED.formatted(MERCEDES.getBrand().getName(), MERCEDES.getRegNumber()), illegalArgumentException.getMessage());
    }

    @Test
    void testCancelBooking_whenCancelExistingBooking_thenCarBookingIsCanceled() {
        MERCEDES.book(BOOKING);
        MERCEDES.cancelBooking();

        assertFalse(MERCEDES.isBooked());
    }

    @Test
    void testCancelBooking_whenCancelNonExistentBooking_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, MERCEDES::cancelBooking);
        assertEquals(NO_BOOKING_FOR_CAR.formatted(MERCEDES.getBrand().getName(), MERCEDES.getRegNumber()), illegalArgumentException.getMessage());
    }
}