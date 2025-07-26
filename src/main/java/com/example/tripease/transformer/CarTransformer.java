package com.example.tripease.transformer;

import com.example.tripease.dto.request.CarRequest;
import com.example.tripease.dto.response.CarResponse;
import com.example.tripease.model.Car;
import com.example.tripease.model.Driver;

public class CarTransformer {

    public  static CarResponse carToCarResponse(Car car, Driver driver)
    {
        return CarResponse.builder()
                .carNumber(car.getCarNumber())
                .carModel(car.getCarModel())
                .perKmRate(car.getPerKmRate())
                .available(car.isAvailable())
                .driver(DriverTransformer.driverToDriverResponse(driver))
                .build();
    }

    public static Car carRequestToCar(CarRequest carRequest)
    {
        return Car.builder()
                .carNumber(carRequest.getCarNumber())
                .carModel(carRequest.getCarModel())
                .perKmRate(carRequest.getPerKmRate())
                .available(true)
                .build();
    }
}
