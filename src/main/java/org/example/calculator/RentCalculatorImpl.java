package org.example.calculator;

import org.example.model.Booking;

import java.time.temporal.ChronoUnit;

public class RentCalculatorImpl implements RentCalculator {

    @Override
    public double getTotalRentalPrice(Booking booking) {
        double rentalPrice = booking.getCar().getRentalPrice().doubleValue();

        long numberOfRentDays = ChronoUnit.DAYS
                .between(booking.getBookedAt(), booking.getCancelAt());

        return rentalPrice * numberOfRentDays;
    }
}
