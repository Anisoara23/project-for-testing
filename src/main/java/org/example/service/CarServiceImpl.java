package org.example.service;

import org.example.dao.CarDao;
import org.example.dto.CarDto;
import org.example.mapper.CarMapper;
import org.example.model.Car;
import org.example.validator.RegNumberValidator;

import java.util.List;

import static org.example.validator.RentalPriceValidator.validateRentalPrice;

public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;

    private final CarDao carDao;

    public CarServiceImpl(CarMapper carMapper, CarDao carDao) {
        this.carMapper = carMapper;
        this.carDao = carDao;
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carDao.getAllCars();
        return cars.stream()
                .map(carMapper::carToCarDto)
                .toList();
    }

    @Override
    public List<CarDto> getElectricCars() {
        List<Car> electricCars = carDao.getElectricCars();
        return electricCars.stream()
                .map(carMapper::carToCarDto)
                .toList();
    }

    @Override
    public CarDto addCar(CarDto carDto) {

        validateCar(carDto);

        Car car = carMapper.carDtoToCar(carDto);
        Car addedCar = carDao.addCar(car);
        return carMapper.carToCarDto(addedCar);
    }

    @Override
    public CarDto getCarByRegNumber(String regNumber) {
        Car car = carDao.getCarByRegNumber(regNumber)
                .orElseThrow(() -> new IllegalArgumentException("No car with reg number = %s".formatted(regNumber)));

        return carMapper.carToCarDto(car);
    }

    @Override
    public void deleteCar(CarDto carDto) {
        Car carToDelete = carDao.getAllCars().stream()
                .filter(car -> car.equals(carMapper.carDtoToCar(carDto)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No such car"));

        carDao.deleteCarById(carToDelete.getId());
    }

    private void validateCar(CarDto carDto) {
        validateRegNumber(carDto);
        validateRentalPrice(carDto.getRentalPrice());
    }

    private void validateRegNumber(CarDto carDto) {
        if (!RegNumberValidator.isRegNumberValid(carDto.getRegNumber())) {
            throw new IllegalArgumentException("Invalid reg number!");
        }
        if (carDao.getCarByRegNumber(carDto.getRegNumber()).isPresent()) {
            throw new IllegalArgumentException("Reg Number is already used");
        }
    }
}
