package org.example.dto;

import org.example.model.Brand;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDto {

    private String regNumber;

    private BigDecimal rentalPrice;

    private Brand brand;

    private boolean isElectric;

    public CarDto(String regNumber, BigDecimal rentalPrice, Brand brand, boolean isElectric) {
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.brand = brand;
        this.isElectric = isElectric;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return isElectric == carDto.isElectric && Objects.equals(regNumber, carDto.regNumber) && Objects.equals(rentalPrice, carDto.rentalPrice) && brand == carDto.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPrice, brand, isElectric);
    }
}
