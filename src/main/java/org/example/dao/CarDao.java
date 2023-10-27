package org.example.dao;

import org.example.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    List<Car> getAllCars();

    List<Car> getElectricCars();

    Car addCar(Car car);

    Optional<Car> getCarByRegNumber(String regNumber);

    Optional<Car> deleteCarById(int id);
}
