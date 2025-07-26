package com.example.tripease.dto.response;


import com.example.tripease.Enum.TripStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {

    private String pickup;
    private  String destination;
    private double tripDistanceInkm;
    private TripStatus tripStatus;
    private double billAmount;

    private Date bookedAt;

    private Date lastUpdateAt;

    CustomerResponse customerResponse;
    CarResponse carResponse;
}
