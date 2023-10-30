package org.example.service;

import org.example.dto.CarDto;

import java.util.List;

public interface CarService {

    List<CarDto> getAllCars();

    List<CarDto> getElectricCars();

    CarDto addCar(CarDto carDto);

    CarDto getCarByRegNumber(String regNumber);

    void deleteCar(CarDto carDto);
}
