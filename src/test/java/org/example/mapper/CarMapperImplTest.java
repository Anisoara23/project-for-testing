package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.MERCEDES;
import static utils.TestUtils.MERCEDES_DTO;

class CarMapperImplTest {

    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        carMapper = new CarMapperImpl();
    }

    @Test
    void testCarToCarDto() {
        CarDto mappedCar = carMapper.carToCarDto(MERCEDES);
        assertEquals(MERCEDES_DTO, mappedCar);
    }

    @Test
    void testCarDtoToCar() {
        Car mappedCar = carMapper.carDtoToCar(MERCEDES_DTO);
        assertEquals(MERCEDES, mappedCar);
    }
}