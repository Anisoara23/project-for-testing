package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.Car;

public interface CarMapper {

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDto);
}
