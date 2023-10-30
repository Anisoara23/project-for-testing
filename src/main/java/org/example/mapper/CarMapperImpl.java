package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.Car;

public class CarMapperImpl implements CarMapper{

    @Override
    public CarDto carToCarDto(Car car) {
        return new CarDto(
                car.getRegNumber(),
                car.getRentalPrice(),
                car.getBrand(),
                car.isElectric(),
                car.isBooked()
        );
    }

    @Override
    public Car carDtoToCar(CarDto carDto) {
        return new Car(
                carDto.getRegNumber(),
                carDto.getRentalPrice(),
                carDto.getBrand(),
                carDto.isElectric(),
                carDto.isBooked()
        );
    }
}
