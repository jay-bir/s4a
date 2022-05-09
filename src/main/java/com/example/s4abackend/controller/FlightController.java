package com.example.s4abackend.controller;

import com.example.s4abackend.service.FlightService;
import com.example.s4abackend.util.FlightCargoWeightInfo;
import com.example.s4abackend.util.WeightUnit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping
    @RequestMapping("/{flightNumber}")
    public ResponseEntity<FlightCargoWeightInfo> getFlightCargoWeight(
            @PathVariable int flightNumber,
            @RequestParam(required = false) Optional<OffsetDateTime> date,
            @RequestParam(required = false) Optional<WeightUnit> unit){

        var flightDate = date.orElse(OffsetDateTime.now());
        var weightUnit = unit.orElse(WeightUnit.KILOGRAM);
        ResponseEntity<FlightCargoWeightInfo> response;

        FlightCargoWeightInfo info;
        try{
            info = this.flightService.getFlightWeightInfo(flightNumber,flightDate, weightUnit);
            response = new ResponseEntity<>(info, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
