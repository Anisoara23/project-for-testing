package org.example.repository;

import org.example.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> getAllCars();

    List<Car> getElectricCars();

    Car addCar(Car mercedes);

    Optional<Car> getCarByRegNumber(String regNumber);

    Optional<Car> deleteCarById(int id);
}
