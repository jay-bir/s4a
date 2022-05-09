package com.example.s4abackend.util;

public class AirportFlightBaggageInfo {

    private final IATACode airport;
    private final int departingFlightsCount;
    private final int arrivingFlightsCount;
    private final int departingBaggage;
    private final int arrivingBaggage;

    public AirportFlightBaggageInfo(IATACode airport, int departingFlightsCount, int arrivingFlightsCount, int departingBaggage, int arrivingBaggage) {
        this.airport = airport;
        this.departingFlightsCount = departingFlightsCount;
        this.arrivingFlightsCount = arrivingFlightsCount;
        this.departingBaggage = departingBaggage;
        this.arrivingBaggage = arrivingBaggage;
    }
}
