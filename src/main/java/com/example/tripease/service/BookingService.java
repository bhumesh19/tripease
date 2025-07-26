package com.example.tripease.service;


import com.example.tripease.Enum.TripStatus;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.excepion.NotFoundException;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Car;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.BookingRepository;
import com.example.tripease.repository.CarRepository;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender javaMailSender;
    public BookingResponse cabBook(BookingRequest bookingRequest, int customerId) {

        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty())
        {
            throw new NotFoundException("Customer not found");
        }

        Customer customer=optionalCustomer.get();
        Car availableCar=carRepository.getAvailableCarRandomly();

        if(availableCar==null)
        {
            throw  new NotFoundException("cab not available");
        }

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest,availableCar.getPerKmRate());
        Booking savedBooking=bookingRepository.save(booking);
        availableCar.setAvailable(false);
        customer.getBookings().add(booking);
        Driver driver = driverRepository.getDriverbyCabId(availableCar.getCarId());
        driver.getBookings().add(booking);

        Customer savedcustomer1=customerRepository.save(customer);
        Driver saveddriver1=driverRepository.save(driver);

        sendEmail(savedcustomer1);

        return BookingTransformer.bookingToBookingResponse(savedBooking,savedcustomer1,availableCar,saveddriver1);


    }

    private  void sendEmail(Customer customer)
    {

        String text="congrates "+customer.getName()+" your cab is booked";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hello5678@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("cab Booked");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public BookingResponse completeBooking(int bookingId) {

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }

        Booking booking = optionalBooking.get();
        if(booking.getTripStatus()==TripStatus.COMPLETED)
        {
            throw new NotFoundException("Already done with your Booking");
        }
        booking.setTripStatus(TripStatus.COMPLETED);

        // Use repository queries to fetch driverId and carId
        Integer driverId = bookingRepository.findDriverIdByBookingId(bookingId);
        Integer customerId = bookingRepository.findCarIdByBookingId(bookingId);

        if (driverId == null || customerId==null) {
            throw new NotFoundException("Driver ID or Car ID not found for this booking");
        }

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
//        Car car=optionalDriver.get
//        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalDriver.isEmpty()|| optionalCustomer.isEmpty()) {
            throw new NotFoundException("Driver or Car not found");
        }
       Driver driver=optionalDriver.get();
        Customer customer=optionalCustomer.get();

        Car car = driver.getCab();
        if (car == null) {
            throw new NotFoundException("Driver is not assigned to any cab");
        }

        car.setAvailable(true);

        //driverRepository.save(driver);
        carRepository.save(car);
        bookingRepository.save(booking);
        return  BookingTransformer.bookingToBookingResponse(booking,customer,car,driver);
    }

    public String totalfair(int driverId) {

       List<Booking> optional=bookingRepository.findByDriverIdandDate(driverId);


        if(optional.isEmpty())
        {
            return "Driver not found";
        }
        int tripdist=0;
        int totalfair=0;
        for(Booking booking:optional)
        {
           tripdist+= booking.getTripDistanceInkm();
           totalfair+=booking.getBillAmount();
        }

return " the toatal dist is "+tripdist+" totalfair "+totalfair;


    }
}
