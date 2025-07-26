package com.example.tripease.controller;

import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

     @PostMapping("/book/customer/{customerId}")
    public BookingResponse cabBook(@RequestBody BookingRequest bookingRequest, @PathVariable int customerId)
    {
        return bookingService.cabBook(bookingRequest,customerId);
    }

    @PutMapping("/complete/{bookingId}")
    public BookingResponse completeBooking(@PathVariable("bookingId") int bookingId)
    {

      return  bookingService.completeBooking(bookingId);

    }

    @GetMapping("/get")
    public String totalfair(@RequestParam("driverId") int driverId)
    {
        return bookingService.totalfair(driverId);
    }
    //@RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd")Date date

    //find those cars which are booked more than 10 times.


}
