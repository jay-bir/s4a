package com.example.s4abackend.model;

import com.example.s4abackend.util.WeightUnit;
import com.example.s4abackend.util.WeightUnitConverter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "cargo_space_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="cargo_item_type", discriminatorType = DiscriminatorType.STRING)
public class CargoSpaceItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "weight")
    private BigDecimal weight;

    @NotNull
    @Column(name = "unit")
    private WeightUnit weightUnit;

    @Min(1)
    @Max(999)
    @Column(name = "pieces")
    private int pieces;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private CargoEntity cargoEntity;



    public BigDecimal getWeight() {
        return this.weight;
    }

    public int getPieces(){
        return this.pieces;
    }

    public BigDecimal getWeight(WeightUnit unit){
        BigDecimal r_weight = this.weight;
        if(unit != this.weightUnit)
            r_weight = WeightUnitConverter.calculateWeight(this.weight, this.weightUnit, unit);
        return r_weight;
    }

}
