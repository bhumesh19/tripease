package com.example.tripease.service;


import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.excepion.NotFoundException;
import com.example.tripease.model.Customer;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer= CustomerTransformer.customerRequestToCustomer(customerRequest);
          Customer savedcustomer =  customerRepository.save(customer);
          CustomerResponse customerResponse=CustomerTransformer.customerToCustomerResponse(savedcustomer);
          return  customerResponse;
    }

    public CustomerResponse getCustomer(int id) {

        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isEmpty())
        {
            throw new NotFoundException("Not found");
        }
        Customer customer=optionalCustomer.get();
        return CustomerTransformer.customerToCustomerResponse(customer);
    }


    public List<CustomerResponse> getGender(Gender gender) {
        Optional<List<Customer>> optionalCustomers=customerRepository.findByGender(gender);
        if(optionalCustomers.isEmpty())
        {
            throw new NotFoundException("not found");
        }
        List<Customer> customers=optionalCustomers.get();
        List<CustomerResponse> responses = new ArrayList<>();

        for (Customer customer : customers) {
            responses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }

        return responses;
    }

    public List<CustomerResponse> getGenderandAge(Gender gender, int age) {

        List<Customer> customers=customerRepository.findByGenderAndAge(gender,age);
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer: customers)
        {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return  customerResponses;
    }

    public List<CustomerResponse> getGenderandAgeGreater(String gender, int age) {

        List<Customer> customers=customerRepository.getGenderandAgeGreater(gender,age);
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer: customers)
        {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return  customerResponses;

    }
}
