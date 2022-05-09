package com.example.s4abackend.repository;


import com.example.s4abackend.model.FlightEntity;
import com.example.s4abackend.util.IATACode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {


     Optional<FlightEntity> findByFlightNumberAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
            int flightNumber, OffsetDateTime dayStart, OffsetDateTime nextDay
     );

     List<FlightEntity> findAllByArrivalAirportIATACodeAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
             IATACode iataCode, OffsetDateTime dayStart, OffsetDateTime nextDay
     );

     List<FlightEntity> findAllByDepartureAirportIATACodeAndDepartureDateGreaterThanEqualAndDepartureDateIsLessThan(
             IATACode iataCode, OffsetDateTime dayStart, OffsetDateTime nextDay
     );

}
