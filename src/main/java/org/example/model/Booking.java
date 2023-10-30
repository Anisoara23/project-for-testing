package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Booking {

    private int id;

    private LocalDateTime bookedAt;

    private LocalDateTime cancelAt;

    private User user;

    private Car car;

    private BigDecimal totalRentalPrice;

    public Booking(int id, LocalDateTime bookedAt, LocalDateTime cancelAt, User user, Car car) {
        this.id = id;
        this.bookedAt = bookedAt;
        this.cancelAt = cancelAt;
        this.user = user;
        this.car = car;
    }

    public Booking(LocalDateTime bookedAt, LocalDateTime cancelAt, User user, Car car) {
        this.bookedAt = bookedAt;
        this.cancelAt = cancelAt;
        this.user = user;
        this.car = car;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return this.id;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public LocalDateTime getCancelAt() {
        return cancelAt;
    }

    public Car getCar() {
        return car;
    }

    public double getTotalRentalPrice() {
        double rentalPrice = car.getRentalPrice().doubleValue();
        long numberOfRentDays = ChronoUnit.DAYS.between(bookedAt, cancelAt);

        return rentalPrice * numberOfRentDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookedAt, booking.bookedAt) && Objects.equals(cancelAt, booking.cancelAt) && Objects.equals(user, booking.user) && Objects.equals(car, booking.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookedAt, cancelAt, user, car);
    }
}
