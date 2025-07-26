package com.example.tripease.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarResponse {

    private String carNumber;
    private String carModel;
    private double perKmRate;
    private boolean available;
    private DriverResponse driver;
}
