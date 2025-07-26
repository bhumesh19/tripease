package com.example.tripease.transformer;

import com.example.tripease.Enum.TripStatus;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Car;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest,double perKmRate)
    {
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInkm(bookingRequest.getTripDistanceInkm())
                .tripStatus(TripStatus.BOOKED)
                .billAmount(bookingRequest.getTripDistanceInkm()*perKmRate)
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking, Customer customer, Car car, Driver driver)
    {
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInkm(booking.getTripDistanceInkm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customerResponse(CustomerTransformer.customerToCustomerResponse(customer))
                .carResponse(CarTransformer.carToCarResponse(car,driver))
                .build();
    }
}
