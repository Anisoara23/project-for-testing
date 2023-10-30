package org.example.service;

import org.example.dao.CarDao;
import org.example.dto.CarDto;
import org.example.mapper.CarMapper;
import org.example.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.BMW;
import static utils.TestUtils.BMW_DTO;
import static utils.TestUtils.INVALID_DATA;
import static utils.TestUtils.INVALID_REG_NUMBER;
import static utils.TestUtils.LESS_RENTAL_PRICE;
import static utils.TestUtils.MERCEDES;
import static utils.TestUtils.MERCEDES_DTO;
import static utils.TestUtils.MORE_RENTAL_PRICE;
import static utils.TestUtils.NO_CAR_WITH_REG_NUMBER;
import static utils.TestUtils.REG_NUMBER_IS_ALREADY_USED;
import static utils.TestUtils.SHOULD_BE_EQUAL_OR_LESS_THAN_500;
import static utils.TestUtils.SHOULD_BE_EQUAL_OR_MORE_THAN_50;
import static utils.TestUtils.VALID_REG_NUMBER;
import static utils.TestUtils.VALID_RENTAL_PRICE;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarDao carDao;

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarServiceImpl(carMapper, carDao);
        MERCEDES_DTO.setRegNumber(VALID_REG_NUMBER);
        MERCEDES_DTO.setRentalPrice(VALID_RENTAL_PRICE);
    }

    @Test
    void testGetAllCars_whenGetAllCars_thenReturnListOfCars() {
        when(carDao.getAllCars()).thenReturn(List.of(MERCEDES));
        when(carMapper.carToCarDto(any(Car.class))).thenReturn(MERCEDES_DTO);

        List<CarDto> cars = carService.getAllCars();

        assertTrue(cars.contains(MERCEDES_DTO));
        verify(carDao).getAllCars();
        verify(carMapper).carToCarDto(any(Car.class));
    }

    @Test
    void testGetElectricCars_whenGetElectricCars_thenReturnListOfElectricCars() {
        when(carDao.getElectricCars()).thenReturn(List.of(MERCEDES));
        when(carMapper.carToCarDto(any(Car.class))).thenReturn(MERCEDES_DTO);

        List<CarDto> electricCars = carService.getElectricCars();

        assertTrue(electricCars.contains(MERCEDES_DTO));
        verify(carDao).getElectricCars();
        verify(carMapper).carToCarDto(any(Car.class));
    }

    @Test
    void testAddCar_whenAddCarWithValidInformation_thenCarIsAddedAndReturned() {
        when(carMapper.carDtoToCar(any(CarDto.class))).thenReturn(MERCEDES);
        when(carDao.addCar(any(Car.class))).thenReturn(MERCEDES);
        when(carMapper.carToCarDto(any(Car.class))).thenReturn(MERCEDES_DTO);

        CarDto addedCar = carService.addCar(MERCEDES_DTO);

        assertEquals(MERCEDES_DTO, addedCar);
        verify(carMapper).carDtoToCar(any(CarDto.class));
        verify(carDao).addCar(any(Car.class));
        verify(carMapper).carToCarDto(any(Car.class));
    }

    @Test
    void testAddCar_whenAddCarWithInvalidRegNumber_thenThrow() {
        MERCEDES_DTO.setRegNumber(INVALID_DATA);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.addCar(MERCEDES_DTO));

        assertEquals(INVALID_REG_NUMBER, illegalArgumentException.getMessage());
        verify(carDao, times(0)).addCar(any(Car.class));
    }

    @Test
    void testAddCar_whenAddCarWithUsedRegNumber_thenThrow() {
        when(carDao.getCarByRegNumber(anyString())).thenReturn(Optional.of(BMW));

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.addCar(MERCEDES_DTO));

        assertEquals(REG_NUMBER_IS_ALREADY_USED, illegalArgumentException.getMessage());
        verify(carDao).getCarByRegNumber(anyString());
        verify(carDao, times(0)).addCar(any(Car.class));
    }

    @Test
    void testAddCar_whenAddCarWithLessRentalPrice_thenThrow() {
        MERCEDES_DTO.setRentalPrice(LESS_RENTAL_PRICE);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.addCar(MERCEDES_DTO));

        assertEquals(SHOULD_BE_EQUAL_OR_MORE_THAN_50, illegalArgumentException.getMessage());
        verify(carDao, times(0)).addCar(any(Car.class));
    }

    @Test
    void testAddCar_whenAddCarWithMoreRentalPrice_thenThrow() {
        MERCEDES_DTO.setRentalPrice(MORE_RENTAL_PRICE);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.addCar(MERCEDES_DTO));

        assertEquals(SHOULD_BE_EQUAL_OR_LESS_THAN_500, illegalArgumentException.getMessage());
        verify(carDao, times(0)).addCar(any(Car.class));
    }

    @Test
    void testGetCarByRegNumber_whenGetCarByValidRegNumber_thenReturnTheCar() {
        when(carDao.getCarByRegNumber(anyString())).thenReturn(Optional.of(MERCEDES));
        when(carMapper.carToCarDto(any(Car.class))).thenReturn(MERCEDES_DTO);

        CarDto carByRegNumber = carService.getCarByRegNumber(MERCEDES_DTO.getRegNumber());

        assertEquals(MERCEDES_DTO, carByRegNumber);
        verify(carDao).getCarByRegNumber(anyString());
    }

    @Test
    void testGetCarByRegNumber_whenGetCarByInvalidRegNumber_thenThrow() {
        when(carDao.getCarByRegNumber(anyString())).thenReturn(Optional.empty());

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.getCarByRegNumber(INVALID_DATA));

        assertEquals(NO_CAR_WITH_REG_NUMBER, illegalArgumentException.getMessage());
    }

    @Test
    void testDeleteCar_whenDeleteExistingCar_thenDoNotThrow() {
        when(carDao.getAllCars()).thenReturn(List.of(MERCEDES));
        when(carMapper.carDtoToCar(any(CarDto.class))).thenReturn(MERCEDES);
        when(carDao.deleteCarById(anyInt())).thenReturn(Optional.of(MERCEDES));

        assertDoesNotThrow(() -> carService.deleteCar(MERCEDES_DTO));
        verify(carDao).getAllCars();
        verify(carMapper).carDtoToCar(any(CarDto.class));
        verify(carDao).deleteCarById(anyInt());
    }

    @Test
    void testDeleteCar_whenDeleteNonExistingCar_thenThrow() {
        when(carDao.getAllCars()).thenReturn(List.of(MERCEDES));
        when(carMapper.carDtoToCar(any(CarDto.class))).thenReturn(BMW);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> carService.deleteCar(BMW_DTO));

        assertEquals("No such car", illegalArgumentException.getMessage());
        verify(carDao).getAllCars();
        verify(carMapper).carDtoToCar(any(CarDto.class));
        verify(carDao, times(0)).deleteCarById(anyInt());
    }
}