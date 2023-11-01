package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.Car;

public class CarMapperImpl implements CarMapper{

    @Override
    public CarDto carToCarDto(Car car) {
        return car != null ? new CarDto(
                car.getRegNumber(),
                car.getRentalPrice(),
                car.getBrand(),
                car.isElectric(),
                car.isBooked()
        ): null;
    }

    @Override
    public Car carDtoToCar(CarDto carDto) {
        return carDto != null ? new Car(
                carDto.getRegNumber(),
                carDto.getRentalPrice(),
                carDto.getBrand(),
                carDto.isElectric(),
                carDto.isBooked()
        ) : null;
    }
}
