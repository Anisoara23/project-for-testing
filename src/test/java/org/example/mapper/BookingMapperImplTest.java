package org.example.mapper;

import org.example.dto.BookingDto;
import org.example.dto.CarDto;
import org.example.dto.UserDto;
import org.example.model.Booking;
import org.example.model.Car;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static utils.TestUtils.BOOKING;
import static utils.TestUtils.BOOKING_DTO;
import static utils.TestUtils.JOHN;
import static utils.TestUtils.JOHN_DTO;
import static utils.TestUtils.MERCEDES;
import static utils.TestUtils.MERCEDES_DTO;

@ExtendWith(MockitoExtension.class)
class BookingMapperImplTest {

    @InjectMocks
    private BookingMapperImpl bookingMapper;

    @Mock
    private CarMapper carMapper;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        BOOKING.setUser(JOHN);
    }

    @Test
    void testBookingToBookingDto_whenBookingIsNotNull() {
        when(userMapper.userToUserDto(any(User.class)))
                .thenReturn(JOHN_DTO);
        when(carMapper.carToCarDto(any(Car.class)))
                .thenReturn(MERCEDES_DTO);

        BookingDto mappedBooking = bookingMapper.bookingToBookingDto(BOOKING);

        assertEquals(BOOKING_DTO, mappedBooking);
    }

    @Test
    void testBookingToBookingDto_whenBookingIsNull() {
        BookingDto mappedBooking = bookingMapper.bookingToBookingDto(null);

        assertNull(mappedBooking);
    }

    @Test
    void testBookingDtoToBooking_whenBookingDtoIsNotNull() {
        when(userMapper.userDtoToUser(any(UserDto.class)))
                .thenReturn(JOHN);
        when(carMapper.carDtoToCar(any(CarDto.class)))
                .thenReturn(MERCEDES);

        Booking mappedBooking = bookingMapper.bookingDtoToBooking(BOOKING_DTO);

        assertEquals(BOOKING, mappedBooking);
    }

    @Test
    void testBookingDtoToBooking_whenBookingDtoIsNull() {
        Booking mappedBooking = bookingMapper.bookingDtoToBooking(null);

        assertNull(mappedBooking);
    }
}