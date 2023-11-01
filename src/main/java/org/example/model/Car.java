package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private int id;

    private String regNumber;

    private BigDecimal rentalPrice;

    private Brand brand;

    private boolean isElectric;

    public Car(
            String regNumber,
            BigDecimal rentalPrice,
            Brand brand,
            boolean isElectric
    ) {
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public Car(
            int id, String regNumber,
            BigDecimal rentalPrice,
            Brand brand,
            boolean isElectric
    ) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
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
        return isElectric == car.isElectric &&
                Objects.equals(regNumber, car.regNumber) &&
                Objects.equals(rentalPrice, car.rentalPrice) &&
                brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPrice, brand, isElectric);
    }
}
