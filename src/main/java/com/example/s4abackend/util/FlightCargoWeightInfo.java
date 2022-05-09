package com.example.s4abackend.util;

import java.math.BigDecimal;

public class FlightCargoWeightInfo {

    private final int flightNumber;
    private final BigDecimal cargoWeight;
    private final BigDecimal baggageWeight;
    private final BigDecimal totalWeight;

    public FlightCargoWeightInfo(int flightNumber, BigDecimal cargoWeight, BigDecimal baggageWeight, BigDecimal totalWeight){
        this.flightNumber = flightNumber;
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = totalWeight;
    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public BigDecimal getBaggageWeight() {
        return baggageWeight;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }
}
