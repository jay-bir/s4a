package com.example.s4abackend.model;

import com.example.s4abackend.util.WeightUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "cargo")
public class CargoEntity {

    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;

    @OneToMany(mappedBy = "cargoEntity", cascade = CascadeType.ALL)
    private Set<CargoTypeItems> cargo;

    @OneToMany(mappedBy = "cargoEntity", cascade = CascadeType.ALL)
    private  Set<BaggageTypeItems> baggage;

    public CargoEntity() {
    }

    public CargoEntity(FlightEntity flight, Set<CargoTypeItems> cargo, Set<BaggageTypeItems> baggage) {
        this.flight = flight;
        this.cargo = cargo;
        this.baggage = baggage;
    }

    public BigDecimal getCargoWeight(WeightUnit unit){
        return BigDecimal.valueOf(
                cargo.stream()
                        .mapToDouble(
                                c -> c.getWeight(unit).doubleValue()
                        ).sum()
        );
    }

    public BigDecimal getBaggageWeight(WeightUnit unit){
        return BigDecimal.valueOf(
                baggage.stream()
                        .mapToDouble(
                                b -> b.getWeight(unit).doubleValue()
                        ).sum()
        );
    }

    public BigDecimal getTotalWeight(WeightUnit unit) {
        Set<CargoSpaceItems> cargoSet = Set.copyOf(this.cargo);
        Set<CargoSpaceItems> baggageSet = Set.copyOf(this.baggage);
        return BigDecimal.valueOf(
                Stream.concat(cargoSet.stream(),baggageSet.stream()
                ).mapToDouble(
                        i -> i.getWeight(unit).doubleValue()
                ).sum()
        );
    }

    public int getTotalBaggagePieces(){
        return this.baggage.stream().mapToInt(CargoSpaceItems::getPieces).sum();
    }

}
