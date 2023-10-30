package org.example.mapper;

import org.example.dto.BookingDto;
import org.example.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.BOOKING;
import static utils.TestUtils.BOOKING_DTO;

class BookingMapperImplTest {

    private BookingMapper bookingMapper;

    @BeforeEach
    void setUp() {
        bookingMapper = new BookingMapperImpl();
    }

    @Test
    void testBookingToBookingDto() {
        BookingDto mappedBooking = bookingMapper.bookingToBookingDto(BOOKING);

        assertEquals(BOOKING_DTO, mappedBooking);
    }

    @Test
    void testBookingDtoToBooking() {
        Booking mappedBooking = bookingMapper.bookingDtoToBooking(BOOKING_DTO);

        assertEquals(BOOKING, mappedBooking);
    }
}