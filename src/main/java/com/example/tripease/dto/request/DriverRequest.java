package com.example.tripease.dto.request;


import com.example.tripease.dto.response.DriverResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverRequest {

    private String name;
    private int age;
    private String emailId;


}
