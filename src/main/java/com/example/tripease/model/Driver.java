package com.example.tripease.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;
    private String name;
    private int age;
   // @Column(unique = true,nullable = false) created logic so need of this
    private String emailId;

   // private Gender gender;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    List<Booking> bookings=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    Car cab;


}
