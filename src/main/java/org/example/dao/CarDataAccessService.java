package org.example.dao;

import org.example.model.Car;
import org.example.repository.CarRepository;

import java.util.List;
import java.util.Optional;

public class CarDataAccessService implements CarDao {

    private final CarRepository carRepository;

    public CarDataAccessService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    @Override
    public List<Car> getElectricCars() {
        return carRepository.getElectricCars();
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.addCar(car);
    }

    @Override
    public Optional<Car> getCarByRegNumber(String regNumber) {
        return carRepository.getCarByRegNumber(regNumber);
    }

    @Override
    public Optional<Car> deleteCarById(int id) {
        return carRepository.deleteCarById(id);
    }
}
