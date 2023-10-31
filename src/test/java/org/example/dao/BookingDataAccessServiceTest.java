package org.example.dao;

import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.BOOKING;

@ExtendWith(MockitoExtension.class)
class BookingDataAccessServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingDataAccessService bookingDao;

    @Test
    void testGetAllBookings_whenGetAllBookings_thenReturnListOfBookings() {
        when(bookingRepository.getAllBookings()).thenReturn(List.of(BOOKING));

        List<Booking> bookings = bookingDao.getAllBookings();

        assertTrue(bookings.contains(BOOKING));
        verify(bookingRepository).getAllBookings();
    }

    @Test
    void testGetAllBookings_whenGetAllBookingsFromEmptyRepo_thenReturnEmptyList() {
        when(bookingRepository.getAllBookings()).thenReturn(List.of());

        List<Booking> bookings = bookingDao.getAllBookings();

        assertTrue(bookings.isEmpty());
        verify(bookingRepository).getAllBookings();
    }

    @Test
    void testGetBookingById_whenGetBookingById_thenReturnBooking() {
        when(bookingRepository.getBookingById(anyInt())).thenReturn(Optional.of(BOOKING));

        Optional<Booking> booking = bookingDao.getBookingById(BOOKING.getId());

        assertEquals(BOOKING, booking.get());
        verify(bookingRepository).getBookingById(anyInt());
    }

    @Test
    void testGetBookingById_whenGetBookingByUnknownId_thenReturnNull() {
        when(bookingRepository.getBookingById(anyInt())).thenReturn(Optional.empty());

        Optional<Booking> booking = bookingDao.getBookingById(BOOKING.getId());

        assertTrue(booking.isEmpty());
        verify(bookingRepository).getBookingById(anyInt());
    }

    @Test
    void testAddBooking_whenAddNewBooking_thenReturnAddedBooking() {
        when(bookingRepository.addBooking(any(Booking.class))).thenReturn(BOOKING);

        Booking booking = bookingDao.addBooking(BOOKING);

        assertEquals(BOOKING, booking);
        verify(bookingRepository).addBooking(any(Booking.class));
    }

    @Test
    void testDeleteBookingById_whenDeleteBookingById_thenReturnDeletedBooking() {
        when(bookingRepository.deleteBookingById(anyInt())).thenReturn(Optional.of(BOOKING));

        Optional<Booking> booking = bookingDao.deleteBookingById(BOOKING.getId());

        assertEquals(BOOKING, booking.get());
        verify(bookingRepository).deleteBookingById(anyInt());
    }


    @Test
    void testDeleteBookingById_whenDeleteBookingByUnknownId_thenReturnNull() {
        when(bookingRepository.deleteBookingById(anyInt())).thenReturn(Optional.empty());

        Optional<Booking> booking = bookingDao.deleteBookingById(BOOKING.getId());

        assertTrue(booking.isEmpty());
        verify(bookingRepository).deleteBookingById(anyInt());
    }
}