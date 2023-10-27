package org.example.repository;

import org.example.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(int id);

    Booking addBooking(Booking booking);

    Optional<Booking> deleteBookingById(int id);
}
