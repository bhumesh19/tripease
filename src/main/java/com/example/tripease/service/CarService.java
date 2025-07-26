package com.example.tripease.service;

import com.example.tripease.dto.request.CarRequest;
import com.example.tripease.dto.response.CarResponse;
import com.example.tripease.excepion.NotFoundException;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Car;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.CarRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.CarTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    DriverRepository driverRepository;
    public CarResponse carRegister(CarRequest carRequest, int driverId) {

        Optional<Driver> driver=driverRepository.findById(driverId);
        if(driver.isEmpty())
        {
            throw new NotFoundException("Not Found or Invalid Id");
        }
        Driver driver1=driver.get();
        Car car= CarTransformer.carRequestToCar(carRequest);
        driver1.setCab(car);
        Driver savedCar=driverRepository.save(driver1);
        CarResponse carResponse=CarTransformer.carToCarResponse(savedCar.getCab(),savedCar);
        return carResponse;
    }

    public List<CarResponse> getcar()
    {
        List<Driver>d=driverRepository.findAll();
        List<CarResponse>c=new ArrayList<>();
        for (Driver driver:d)
        {
           if(driver.getBookings().size()>2)
           {
               c.add(CarTransformer.carToCarResponse(driver.getCab(),driver));
           }
        }

return c;
    }
}
