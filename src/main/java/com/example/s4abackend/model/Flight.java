package com.example.s4abackend.model;

import com.example.s4abackend.util.IATACode;

import javax.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number")
    private int flightNumber;

    @Column(name="departure_airport")
    @Enumerated(EnumType.STRING)
    private IATACode departureAirportIATACode;

    @Column(name = "arrival_airport")
    @Enumerated(EnumType.STRING)
    private IATACode arrivalAirportIATACode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Cargo cargo;

}
