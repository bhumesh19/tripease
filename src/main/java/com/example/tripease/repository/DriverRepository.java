package com.example.tripease.repository;

import com.example.tripease.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Optional<Driver> findByEmailId(String emailId);
@Query(value = "select * from Driver where car_id=:carId",nativeQuery = true)
    Driver getDriverbyCabId(@Param("carId") int carId);



}
