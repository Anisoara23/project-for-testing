package org.example.service;

import org.example.dao.BookingDao;
import org.example.dto.BookingDto;
import org.example.dto.CarDto;
import org.example.mapper.BookingMapper;
import org.example.model.Booking;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingDao bookingDao) {
        this.bookingMapper = bookingMapper;
        this.bookingDao = bookingDao;
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingDao.getAllBookings();

        return bookings.stream()
                .map(bookingMapper::bookingToBookingDto)
                .toList();
    }

    @Override
    public BookingDto getBookingById(int id) {
        Optional<Booking> booking = bookingDao.getBookingById(id);

        return bookingMapper.bookingToBookingDto(
                booking.orElseThrow(() -> new IllegalArgumentException("No booking with id = %s".formatted(id)))
        );
    }

    @Override
    public void addBooking(BookingDto bookingDto) {
        CarDto carDto = bookingDto.getCarDto();
        validateBookingAvailability(carDto, bookingDto);
        carDto.setBooked(true);

        Booking bookingToAdd = bookingMapper.bookingDtoToBooking(bookingDto);
        bookingToAdd.getUser().addBooking(bookingToAdd);

        bookingDao.addBooking(bookingToAdd);
    }

    private void validateBookingAvailability(CarDto carDto, BookingDto bookingDto) {
        Optional<BookingDto> optionalBooking = bookingDao.getAllBookings()
                .stream()
                .map(bookingMapper::bookingToBookingDto)
                .filter(booking -> booking.getCarDto().equals(carDto))
                .findAny();


        if (optionalBooking.isPresent() &&
                (bookingDto.getBookedAt().isAfter(optionalBooking.get().getBookedAt()) ||
                        bookingDto.getBookedAt().equals(optionalBooking.get().getBookedAt())) &&
                (bookingDto.getCancelAt().isBefore(optionalBooking.get().getCancelAt()) ||
                        bookingDto.getCancelAt().equals(optionalBooking.get().getCancelAt()))) {
            throw new IllegalArgumentException(String.format("Car %s with reg number %s is booked for period %s-%s",
                    carDto.getBrand(),
                    carDto.getRegNumber(),
                    bookingDto.getBookedAt().toLocalDate(),
                    bookingDto.getCancelAt().toLocalDate()));
        }
    }
}
