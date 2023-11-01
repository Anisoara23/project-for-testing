package org.example.service;

import org.example.dto.BookingDto;

import java.util.List;

public interface BookingService {

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(int id);

    void addBooking(BookingDto bookingDto);
}
