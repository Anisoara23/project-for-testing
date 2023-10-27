package org.example.model;

import java.math.BigDecimal;

public class Car {

    private int id;

    private String regNumber;

    private BigDecimal rentalPrice;

    private Brand brand;

    private boolean isElectric;

    private boolean isBooked;

    public Car() {
    }

    public Car(int id, String regNumber, BigDecimal rentalPrice, Brand brand, boolean isElectric) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public void book(Booking booking) {
        if (isBooked){
            throw new IllegalArgumentException("Car %s with regNumber = %s is already booked.".formatted(brand.getName(), regNumber));
        }
        isBooked = true;
    }

    public void cancelBooking() {
        if (!isBooked){
            throw new IllegalArgumentException("There is no booking for car %s with regNumber = %s".formatted(brand.getName(), regNumber));
        }
        isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getId() {
        return id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Brand getBrand() {
        return brand;
    }
}
