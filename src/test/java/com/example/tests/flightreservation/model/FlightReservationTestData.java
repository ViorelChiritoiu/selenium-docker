package com.example.tests.flightreservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightReservationTestData {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private String zip;
    private String passengersCount;
    private String expectedPrice;
}
