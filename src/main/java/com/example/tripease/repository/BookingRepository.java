package com.example.tripease.repository;

import com.example.tripease.model.Booking;
import com.example.tripease.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Integer> {


    @Query(value = "SELECT driver_id FROM booking  WHERE booking_id = :bookingId",nativeQuery = true)
    Integer findDriverIdByBookingId(@Param("bookingId") int bookingId);

    @Query(value = "SELECT customer_id FROM booking WHERE booking_id = :bookingId",nativeQuery = true)
    Integer findCarIdByBookingId(@Param("bookingId") int bookingId);
    @Query(value = "SELECT * FROM booking WHERE driver_id = :driverId ",nativeQuery = true)
    List<Booking> findByDriverIdandDate(@Param("driverId") int driverId);
}
