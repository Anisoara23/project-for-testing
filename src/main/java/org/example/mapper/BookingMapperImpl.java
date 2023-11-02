package org.example.mapper;

import org.example.calculator.RentCalculator;
import org.example.calculator.RentCalculatorImpl;
import org.example.dto.BookingDto;
import org.example.model.Booking;

import java.math.BigDecimal;

public class BookingMapperImpl implements BookingMapper {

    private final UserMapper userMapper;

    private final CarMapper carMapper;

    public BookingMapperImpl(UserMapper userMapper, CarMapper carMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    @Override
    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        return bookingDto != null ? new Booking(
                bookingDto.getBookedAt(),
                bookingDto.getCancelAt(),
                userMapper.userDtoToUser(bookingDto.getUserDto()),
                carMapper.carDtoToCar(bookingDto.getCarDto())
        ) : null;
    }

    @Override
    public BookingDto bookingToBookingDto(Booking booking) {
        RentCalculator calculator = new RentCalculatorImpl();

        return booking != null ? new BookingDto(
                booking.getBookedAt(),
                booking.getCancelAt(),
                userMapper.userToUserDto(booking.getUser()),
                carMapper.carToCarDto(booking.getCar()),
                BigDecimal.valueOf(calculator.getTotalRentalPrice(booking))
        ) : null;
    }
}
