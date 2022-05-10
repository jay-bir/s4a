package com.example.s4abackend.service;

import com.example.s4abackend.model.FlightEntity;
import com.example.s4abackend.repository.FlightRepository;
import com.example.s4abackend.util.AirportFlightBaggageInfo;
import com.example.s4abackend.util.IATACode;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AirportService {

    private final FlightRepository flightRepository;

    public AirportService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public AirportFlightBaggageInfo getFlightAndCargoCount(IATACode airport, OffsetDateTime departureDate){

        var dayStart = departureDate.truncatedTo(ChronoUnit.DAYS);
        var nextDay = departureDate.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        var arrivingFlights = this.flightRepository.findAllByArrivalAirportIATACodeAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
                airport, dayStart, nextDay
        );

        var departingFlights = this.flightRepository.findAllByDepartureAirportIATACodeAndDepartureDateGreaterThanEqualAndDepartureDateIsLessThan(
                airport, dayStart, nextDay
        );

        var arrivingBaggage = arrivingFlights.stream().mapToInt(
                FlightEntity::getTotalBaggagePieces
        ).sum();

        var departingBaggage = departingFlights.stream().mapToInt(
                FlightEntity::getTotalBaggagePieces
        ).sum();

        return new AirportFlightBaggageInfo(
                airport,
                departingFlights.size(),
                arrivingFlights.size(),
                departingBaggage,
                arrivingBaggage
        );
    }
}
