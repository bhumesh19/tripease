package com.example.tripease.controller;


import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.model.Customer;
import com.example.tripease.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        System.out.println("Name: " + customerRequest.getName());
        System.out.println("Age: " + customerRequest.getAge());
        System.out.println("Email: " + customerRequest.getEmailId());

          return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int id)
    {
        return customerService.getCustomer(id);
    }

    @GetMapping("/get/customer-gender/{gender}")
    public List<CustomerResponse> getGender(@PathVariable("gender") Gender gender)
    {
        return customerService.getGender(gender);
    }

    @GetMapping("/get")
    public List<CustomerResponse> getGenderAndAge(@RequestParam("gender") Gender gender,@RequestParam("age") int age)
    {
        return customerService.getGenderandAge(gender,age);
    }

    @GetMapping("/get-by-age")
    public List<CustomerResponse> getGenderAndAgeGreate(@RequestParam("gender") String gender,@RequestParam("age") int age)
    {
        return customerService.getGenderandAgeGreater(gender,age);
    }
}
