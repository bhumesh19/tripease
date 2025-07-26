package com.example.tripease.controller;


import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;
    @GetMapping("/add")
    public ResponseEntity<?> addDriver(@RequestBody DriverRequest driverRequest)
    {
         return driverService.addDriver(driverRequest);
    }

}
