package org.example.dao;

import org.example.model.Booking;
import org.example.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

public class BookingDataAccessService implements BookingDao {

    private final BookingRepository bookingRepository;

    public BookingDataAccessService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public Booking addBooking(Booking booking) {
        return bookingRepository.addBooking(booking);
    }

    @Override
    public Optional<Booking> deleteBookingById(int id) {
        return bookingRepository.deleteBookingById(id);
    }
}
