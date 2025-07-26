package com.example.tripease.service;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.excepion.DuplicateResourceException;
import com.example.tripease.excepion.NotFoundException;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class DriverService {


    @Autowired
    DriverRepository driverRepository;
    public ResponseEntity<?> addDriver(DriverRequest driverRequest) {

        Optional<Driver> existingDriver = driverRepository.findByEmailId(driverRequest.getEmailId());

        if (existingDriver.isPresent()) {
            //throw new DuplicateResourceException("Email is already taken: " + driverRequest.getEmailId());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email is already taken: " + driverRequest.getEmailId()));
        }
        Driver driver= DriverTransformer.driverRequestToDriver(driverRequest);
        Driver saved = driverRepository.save(driver);
        DriverResponse driverResponse=DriverTransformer.driverToDriverResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponse);
    }


}
