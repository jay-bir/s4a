package com.example.s4abackend.repository;

import com.example.s4abackend.model.CargoEntity;
import com.example.s4abackend.model.CargoTypeItems;
import com.example.s4abackend.model.FlightEntity;
import com.example.s4abackend.repository.FlightRepository;
import com.example.s4abackend.util.IATACode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testRetrievingFlightWeightInfo(){
        int flightNumber = 3490;
        OffsetDateTime dateTime = OffsetDateTime.parse("2015-05-15T02:11:30-02:00");

        var dayStart = dateTime.truncatedTo(ChronoUnit.DAYS);
        var nextDay = dateTime.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        FlightEntity fe = new FlightEntity(
                flightNumber,
                IATACode.ANC,
                IATACode.GDN,
                dateTime
        );
        CargoEntity ce = new CargoEntity(fe, Collections.emptySet(), Collections.emptySet());
        fe.setCargo(ce);

        this.entityManager.persist(fe);
        this.entityManager.persist(ce);

        this.entityManager.flush();

        FlightEntity returned = this.flightRepository.findByFlightNumberAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
                flightNumber, dayStart, nextDay
        ).orElse(null);

        Assertions.assertEquals(fe.getFlightNumber(), returned.getFlightNumber());
        Assertions.assertEquals(dateTime, returned.getDepartureDate());
    }
}
