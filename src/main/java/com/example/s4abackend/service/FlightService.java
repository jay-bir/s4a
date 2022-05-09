package com.example.s4abackend.service;

import com.example.s4abackend.repository.FlightRepository;
import com.example.s4abackend.util.FlightCargoWeightInfo;
import com.example.s4abackend.util.WeightUnit;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class FlightService {


    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public FlightCargoWeightInfo getFlightWeightInfo(int flightNumber, OffsetDateTime departureDate){
        return this.getFlightWeightInfo(flightNumber, departureDate, WeightUnit.KILOGRAM);
    }

    public FlightCargoWeightInfo getFlightWeightInfo(int flightNumber, OffsetDateTime departureDate, WeightUnit unit) throws RuntimeException{
        var dayStart = departureDate.truncatedTo(ChronoUnit.DAYS);
        var nextDay = departureDate.truncatedTo(ChronoUnit.DAYS).plusDays(1);
        var flight = this.flightRepository
                .findByFlightNumberAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
                        flightNumber,
                        dayStart,
                        nextDay

                ).orElseThrow(() -> new RuntimeException("Flight not found"));
        return flight.getWeightInfo(unit);
    }
}
