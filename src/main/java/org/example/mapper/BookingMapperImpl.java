package org.example.mapper;

import org.example.dto.BookingDto;
import org.example.model.Booking;

public class BookingMapperImpl implements BookingMapper{

    @Override
    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        return new Booking(
                bookingDto.getBookedAt(),
                bookingDto.getCancelAt(),
                bookingDto.getUser(),
                bookingDto.getCar()
        );
    }

    @Override
    public BookingDto bookingToBookingDto(Booking booking) {
        return new BookingDto(
                booking.getBookedAt(),
                booking.getCancelAt(),
                booking.getUser(),
                booking.getCar(),
                booking.getTotalRentalPrice()
        );
    }
}
