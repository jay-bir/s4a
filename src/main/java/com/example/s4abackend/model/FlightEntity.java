package com.example.s4abackend.model;

import com.example.s4abackend.util.FlightCargoWeightInfo;
import com.example.s4abackend.util.IATACode;
import com.example.s4abackend.util.WeightUnit;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Table(name = "flight")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1000)
    @Max(9999)
    @Column(name = "flight_number")
    private int flightNumber;

    @NotNull
    @Column(name="departure_airport")
    @Enumerated(EnumType.STRING)
    private IATACode departureAirportIATACode;

    @NotNull
    @Column(name = "arrival_airport")
    @Enumerated(EnumType.STRING)
    private IATACode arrivalAirportIATACode;

    @Column(name = "departure_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime departureDate;

    @OneToOne(mappedBy = "flight", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CargoEntity cargo;

    public FlightEntity() {

    }

    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    public FlightEntity(int flightNumber,
                        IATACode departureAirportIATACode,
                        IATACode arrivalAirportIATACode,
                        OffsetDateTime departureDate
                        ) {
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

    public FlightCargoWeightInfo getWeightInfo(WeightUnit unit){
        return new FlightCargoWeightInfo(
                this.flightNumber,
                this.cargo.getCargoWeight(unit),
                this.cargo.getBaggageWeight(unit),
                this.cargo.getTotalWeight(unit)
        );
    }

    public int getTotalBaggagePieces(){
        return this.cargo.getTotalBaggagePieces();
    }

}
