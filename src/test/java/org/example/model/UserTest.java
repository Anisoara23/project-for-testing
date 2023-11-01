package org.example.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.BOOKING;
import static utils.TestUtils.JOHN;

class UserTest {

    @BeforeEach
    void setUp() {
        JOHN.removeBooking(BOOKING);
    }

    @Test
    void testAddBooking_whenAddNewBooking_thenBookingIsAddedInTheUserBookings() {
        JOHN.addBooking(BOOKING);

        assertTrue(JOHN.getBookings().contains(BOOKING));
    }

    @Test
    void testAddBooking_whenAddExistingBooking_thenBookingIsNotAdded() {
        JOHN.addBooking(BOOKING);
        JOHN.addBooking(BOOKING);

        assertTrue(JOHN.getBookings().contains(BOOKING));
        assertEquals(1, JOHN.getBookings().size());
    }

    @Test
    void testRemoveBooking_whenRemoveExistingBooking_thenBookingIsRemoved() {
        JOHN.addBooking(BOOKING);
        System.out.println(JOHN.getBookings());
        JOHN.removeBooking(BOOKING);
        System.out.println(JOHN.getBookings().contains(BOOKING));
        System.out.println(BOOKING);
        System.out.println(JOHN.getBookings());

        List<Booking> bookings = JOHN.getBookings();

        assertTrue(bookings.isEmpty());
    }

    @Test
    void testRemoveBooking_whenRemoveUnExistingBooking_thenDoNothing() {
        assertDoesNotThrow(() -> JOHN.removeBooking(BOOKING));
    }
}