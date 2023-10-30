package org.example.dto;

import org.example.model.Brand;

import java.math.BigDecimal;

public class CarDto {

    private String regNumber;

    private BigDecimal rentalPrice;

    private Brand brand;

    private boolean isElectric;

    private boolean isBooked;

    public CarDto(String regNumber, BigDecimal rentalPrice, Brand brand, boolean isElectric, boolean isBooked) {
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
        this.isBooked = isBooked;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public Brand getBrand() {
        return brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
