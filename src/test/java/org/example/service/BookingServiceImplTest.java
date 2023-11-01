package org.example.service;

import org.example.dao.BookingDao;
import org.example.dto.BookingDto;
import org.example.dto.CarDto;
import org.example.dto.UserDto;
import org.example.mapper.BookingMapper;
import org.example.model.Booking;
import org.example.model.Brand;
import org.example.model.Car;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingDao bookingDao;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Captor
    private ArgumentCaptor<Booking> bookingArgumentCaptor;

    private LocalDateTime bookedAt;

    private LocalDateTime cancelAt;

    private BookingDto bookingDto;

    private Booking booking;

    private User user;

    private UserDto userDto;

    private Car car;

    private CarDto carDto;

    @BeforeEach
    void setUp() {
        bookedAt = LocalDateTime.of(2023, 10, 20, 10, 10);
        cancelAt = LocalDateTime.of(2023, 10, 25, 10, 10);

        user = new User(1,
                "John",
                "Smith",
                23,
                "johny@mail.com",
                "098-968-586");
        userDto = new UserDto("John",
                "Smith",
                23,
                "johny@mail.com",
                "098-968-586");

        car = new Car(1,
                "ABC123",
                new BigDecimal("100"),
                Brand.MERCEDES,
                true);

        carDto = new CarDto("ABC123",
                new BigDecimal("100"),
                Brand.MERCEDES,
                true);

        bookingDto = new BookingDto(bookedAt, cancelAt, userDto, carDto);
        booking = new Booking(1, bookedAt, cancelAt, user, car);
    }

    @Test
    void testGetAllBookings_whenGetAllBookings_thenReturnListOfBookings() {
        when(bookingDao.getAllBookings()).thenReturn(List.of(booking));
        when(bookingMapper.bookingToBookingDto(any(Booking.class))).thenReturn(bookingDto);

        List<BookingDto> bookings = bookingService.getAllBookings();

        assertTrue(bookings.contains(bookingDto));
        verify(bookingDao).getAllBookings();
        verify(bookingMapper).bookingToBookingDto(any(Booking.class));
    }

    @Test
    void testGetBookingById_whenGetBookingByValidId_thenReturnBooking() {
        when(bookingDao.getBookingById(anyInt())).thenReturn(Optional.of(booking));
        when(bookingMapper.bookingToBookingDto(any(Booking.class))).thenReturn(bookingDto);

        BookingDto bookingById = bookingService.getBookingById(booking.getId());

        assertEquals(bookingDto, bookingById);
        verify(bookingDao).getBookingById(anyInt());
        verify(bookingMapper).bookingToBookingDto(any(Booking.class));
    }

    @Test
    void testGetBookingById_whenGetBookingByInvalidId_thenThrow() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> bookingService.getBookingById(booking.getId()));

        assertEquals("No booking with id = %s".formatted(booking.getId()), illegalArgumentException.getMessage());
        verify(bookingDao).getBookingById(anyInt());
        verifyNoInteractions(bookingMapper);
    }

    @Test
    void testAddBooking_whenAddValidBooking_thenBookingIsAdded_andAllInfoIsValid() {
        when(bookingMapper.bookingDtoToBooking(any(BookingDto.class))).thenReturn(booking);

        bookingService.addBooking(bookingDto);

        verify(bookingDao).addBooking(bookingArgumentCaptor.capture());
        Booking addedBooking = bookingArgumentCaptor.getValue();

        assertEquals(booking, addedBooking);
    }

    @Test
    void testAddBooking_whenAddBookingForPeriodWhenCarIsAlreadyBooked_thenThrow() {
        when(bookingDao.getAllBookings()).thenReturn(List.of(booking));
        when(bookingMapper.bookingToBookingDto(any())).thenReturn(bookingDto);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> bookingService.addBooking(bookingDto));

        assertEquals("Car %s with reg number %s is booked for period %s-%s".formatted(
                car.getBrand(),
                car.getRegNumber(),
                bookedAt.toLocalDate(),
                cancelAt.toLocalDate()), illegalArgumentException.getMessage());
    }

    @Test
    void testAddBooking_whenAddBookingForUser_thenUserShouldHaveTheBookingInItsListOfBookings() {
        when(bookingMapper.bookingDtoToBooking(any(BookingDto.class))).thenReturn(booking);
        when(bookingDao.addBooking(any(Booking.class))).thenReturn(booking);

        bookingService.addBooking(bookingDto);
        verify(bookingDao).addBooking(bookingArgumentCaptor.capture());
        Booking addedBooking = bookingArgumentCaptor.getValue();

        assertTrue(addedBooking.getUser().getBookings().contains(addedBooking));
    }
}