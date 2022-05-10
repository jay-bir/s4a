package com.example.s4abackend.controller;


import com.example.s4abackend.service.AirportService;
import com.example.s4abackend.util.AirportFlightBaggageInfo;
import com.example.s4abackend.util.IATACode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }


    @GetMapping("/{iata}")
    public ResponseEntity<AirportFlightBaggageInfo> getAirportFlightInfo(
            @PathVariable(name = "iata")IATACode iata,
            @RequestParam(name = "date", required = false) Optional<OffsetDateTime> date){

        AirportFlightBaggageInfo info = this.airportService.getFlightAndCargoCount(
                iata,
                date.orElse(OffsetDateTime.now())
        );
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
