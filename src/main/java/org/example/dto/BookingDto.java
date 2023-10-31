package org.example.dto;

import org.example.model.Car;
import org.example.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookingDto {

    private int id;

    private LocalDateTime bookedAt;

    private LocalDateTime cancelAt;

    private User user;

    private Car car;

    private BigDecimal totalRentalPrice;

    public BookingDto(LocalDateTime bookedAt, LocalDateTime cancelAt, User user, Car car, BigDecimal totalRentalPrice) {
        this.bookedAt = bookedAt;
        this.cancelAt = cancelAt;
        this.user = user;
        this.car = car;
        this.totalRentalPrice = totalRentalPrice;
    }

    public BookingDto(LocalDateTime bookedAt, LocalDateTime cancelAt,User user, Car car) {
        this.bookedAt = bookedAt;
        this.user = user;
        this.car = car;
        this.cancelAt = cancelAt;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public LocalDateTime getCancelAt() {
        return cancelAt;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public void setCancelAt(LocalDateTime cancelAt) {
        this.cancelAt = cancelAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(bookedAt, that.bookedAt) && Objects.equals(cancelAt, that.cancelAt) && Objects.equals(user, that.user) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookedAt, cancelAt, user, car);
    }
}
