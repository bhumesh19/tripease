package com.example.tripease.repository;

import com.example.tripease.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {



    @Query("select c from Car c where c.available=true order by rand() limit 1 ")
    Car getAvailableCarRandomly();
}
