package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.BOOKING;
import static utils.TestUtils.BOOKING_IS_ALREADY_USED_BY_OTHER_USER;
import static utils.TestUtils.BOOKING_IS_ALREADY_USED_BY_THIS_USER;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.MARIA;
import static utils.TestUtils.NO_SUCH_BOOKING;

class UserTest {

    @BeforeEach
    void setUp() {
        if (JOHN.getBookings().contains(BOOKING)) {
            JOHN.removeBooking(BOOKING);
        }

        if (MARIA.getBookings().contains(BOOKING)) {
            MARIA.removeBooking(BOOKING);
        }
    }

    @Test
    void testAddBooking_whenAddNewBooking_thenBookingIsAddedInTheUserBookings() {
        JOHN.addBooking(BOOKING);

        assertTrue(JOHN.getBookings().contains(BOOKING));
    }

    @Test
    void testAddBooking_whenAddExistingBooking_thenThrow() {
        JOHN.addBooking(BOOKING);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> JOHN.addBooking(BOOKING));
        assertEquals(BOOKING_IS_ALREADY_USED_BY_THIS_USER, illegalArgumentException.getMessage());
    }

    @Test
    void testAddBooking_whenAddNonAvailableBooking_thenThrow() {
        MARIA.addBooking(BOOKING);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> JOHN.addBooking(BOOKING));
        assertEquals(BOOKING_IS_ALREADY_USED_BY_OTHER_USER, illegalArgumentException.getMessage());
    }

    @Test
    void testRemoveBooking_whenRemoveExistingBooking_thenBookingIsRemoved() {
        JOHN.addBooking(BOOKING);
        JOHN.removeBooking(BOOKING);

        List<Booking> bookings = JOHN.getBookings();

        assertTrue(bookings.isEmpty());
    }

    @Test
    void testRemoveBooking_whenRemoveUnExistingBooking_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> JOHN.removeBooking(BOOKING));
        assertEquals(NO_SUCH_BOOKING, illegalArgumentException.getMessage());
    }

    @Test
    void testRemoveBooking_whenRemoveBookingForAUser_thenBookingIsAvailableForAnotherUser() {
        JOHN.addBooking(BOOKING);
        JOHN.removeBooking(BOOKING);

        MARIA.addBooking(BOOKING);
        List<Booking> bookings = MARIA.getBookings();

        assertTrue(bookings.contains(BOOKING));
    }
}