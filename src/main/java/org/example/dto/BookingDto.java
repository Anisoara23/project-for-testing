package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookingDto {

    private int id;

    private LocalDateTime bookedAt;

    private LocalDateTime cancelAt;

    private UserDto userDto;

    private CarDto carDto;

    private BigDecimal totalRentalPrice;

    public BookingDto(
            LocalDateTime bookedAt,
            LocalDateTime cancelAt,
            UserDto userDto,
            CarDto carDto,
            BigDecimal totalRentalPrice
    ) {
        this.bookedAt = bookedAt;
        this.cancelAt = cancelAt;
        this.userDto = userDto;
        this.carDto = carDto;
        this.totalRentalPrice = totalRentalPrice;
    }

    public BookingDto(
            LocalDateTime bookedAt,
            LocalDateTime cancelAt,
            UserDto userDto,
            CarDto carDto
    ) {
        this.bookedAt = bookedAt;
        this.userDto = userDto;
        this.carDto = carDto;
        this.cancelAt = cancelAt;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public LocalDateTime getCancelAt() {
        return cancelAt;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(bookedAt, that.bookedAt) &&
                Objects.equals(cancelAt, that.cancelAt) &&
                Objects.equals(userDto, that.userDto) &&
                Objects.equals(carDto, that.carDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookedAt, cancelAt, userDto, carDto);
    }
}
