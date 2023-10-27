package org.example.dao;

import org.example.model.Car;
import org.example.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.BMW;
import static utils.TestUtils.MERCEDES;

@ExtendWith(MockitoExtension.class)
class CarDataAccessServiceTest {

    private CarDao carDao;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carDao = new CarDataAccessService(carRepository);
    }

    @Test
    void testGetAllCars_whenGetAllCarsFromNonEmptyDatabase_thenReturnListOfCars() {
        List<Car> cars = List.of(MERCEDES, BMW);
        when(carRepository.getAllCars()).thenReturn(cars);

        List<Car> carList = carDao.getAllCars();

        assertFalse(carList.isEmpty());
        assertTrue(carList.containsAll(cars));
        verify(carRepository).getAllCars();
    }

    @Test
    void testGetAllCars_whenGetAllCarsFromEmptyDatabase_thenReturnEmptyList() {
        when(carRepository.getAllCars()).thenReturn(List.of());

        List<Car> cars = carDao.getAllCars();

        assertTrue(cars.isEmpty());
        verify(carRepository).getAllCars();
    }

    @Test
    void testGetElectricCars_whenGetElectricCars_thenReturnListOfElectricCars() {
        when(carRepository.getElectricCars()).thenReturn(List.of(MERCEDES));

        List<Car> electricCars = carDao.getElectricCars();
        assertTrue(electricCars.contains(MERCEDES));
        verify(carRepository).getElectricCars();
    }

    @Test
    void testAddCar_whenAddNewCar_thenAddedCarIsReturned() {
        when(carRepository.addCar(any(Car.class))).thenReturn(MERCEDES);

        Car car = carDao.addCar(MERCEDES);
        assertEquals(MERCEDES, car);
        verify(carRepository).addCar(any(Car.class));
    }

    @Test
    void testGetCarByRegNumber_whenGetCarByRegNumber_thenReturnTheCar() {
        when(carRepository.getCarByRegNumber(anyString())).thenReturn(Optional.of(MERCEDES));

        Optional<Car> car = carDao.getCarByRegNumber(MERCEDES.getRegNumber());

        assertEquals(MERCEDES, car.get());
        verify(carRepository).getCarByRegNumber(anyString());
    }

    @Test
    void testGetCarByRegNumber_whenGetCarByUnknownRegNumber_thenReturnNull() {
        when(carRepository.getCarByRegNumber(anyString())).thenReturn(Optional.empty());

        Optional<Car> car = carDao.getCarByRegNumber("test");

        assertTrue(car.isEmpty());
        verify(carRepository).getCarByRegNumber(anyString());
    }

    @Test
    void testDeleteCarById_whenDeleteExistingCar_thenReturnDeletedCar() {
        when(carRepository.deleteCarById(anyInt())).thenReturn(Optional.of(MERCEDES));

        Optional<Car> car = carDao.deleteCarById(MERCEDES.getId());

        assertEquals(MERCEDES, car.get());
        verify(carRepository).deleteCarById(anyInt());
    }

    @Test
    void testDeleteCarById_whenDeleteByNonExistingId_thenReturnNull() {
        when(carRepository.deleteCarById(anyInt())).thenReturn(Optional.empty());

        Optional<Car> car = carDao.deleteCarById(100);

        assertTrue(car.isEmpty());
        verify(carRepository).deleteCarById(anyInt());
    }
}