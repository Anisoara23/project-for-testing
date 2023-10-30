package org.example.mapper;

import org.example.dto.BookingDto;
import org.example.model.Booking;

public interface BookingMapper {

    Booking bookingDtoToBooking(BookingDto bookingDto);

    BookingDto bookingToBookingDto(Booking booking);
}
