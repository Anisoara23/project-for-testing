package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private int id;

    private String regNumber;

    private BigDecimal rentalPrice;

    private Brand brand;

    private boolean isElectric;

    private boolean isBooked;

    public Car() {
    }

    public Car(String regNumber, BigDecimal rentalPrice, Brand brand, boolean isElectric, boolean isBooked) {
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
        this.isBooked = isBooked;
    }

    public Car(int id, String regNumber, BigDecimal rentalPrice, Brand brand, boolean isElectric) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public void book() {
        if (!isBooked) {
            isBooked = true;
        }
    }

    public void cancelBooking() {
        if (isBooked) {
            isBooked = false;
        }
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

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isElectric == car.isElectric && isBooked == car.isBooked && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPrice, car.rentalPrice) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPrice, brand, isElectric, isBooked);
    }
}
