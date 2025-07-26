package com.example.tripease.model;

import com.example.tripease.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int bookingId;
    private String pickup;
    private  String destination;
    private double tripDistanceInkm;
    @Enumerated(EnumType.STRING)
    private  TripStatus tripStatus;
    private double billAmount;
    @CreationTimestamp
    private  Date bookedAt;
    @UpdateTimestamp
    private Date lastUpdateAt;


}
