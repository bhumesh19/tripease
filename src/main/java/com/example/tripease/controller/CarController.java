package com.example.tripease.controller;


import com.example.tripease.dto.request.CarRequest;
import com.example.tripease.dto.response.CarResponse;
import com.example.tripease.model.Car;
import com.example.tripease.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/register/{driverId}")
    public CarResponse carRegister(@RequestBody CarRequest carRequest, @PathVariable("driverId") int driverId)
    {
       return carService.carRegister(carRequest,driverId);
    }
    @GetMapping("/getcar")
    public List<CarResponse> getcar()
    {
        return  carService.getcar();
    }
}
