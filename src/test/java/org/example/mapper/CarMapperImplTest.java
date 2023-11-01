package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.TestUtils.BMW;
import static utils.TestUtils.BMW_DTO;

class CarMapperImplTest {

    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        carMapper = new CarMapperImpl();
    }

    @Test
    void testCarToCarDto_whenCarIsNotNull() {
        CarDto mappedCar = carMapper.carToCarDto(BMW);
        assertEquals(BMW_DTO, mappedCar);
    }

    @Test
    void testCarToCarDto_whenCarNotNull() {
        CarDto mappedCar = carMapper.carToCarDto(null);
        assertNull(mappedCar);
    }

    @Test
    void testCarDtoToCar_whenCarDtoIsNotNull() {
        Car mappedCar = carMapper.carDtoToCar(BMW_DTO);
        assertEquals(BMW, mappedCar);
    }

    @Test
    void testCarDtoToCar_whenCarDtoIsNull() {
        Car mappedCar = carMapper.carDtoToCar(null);
        assertNull(mappedCar);
    }
}