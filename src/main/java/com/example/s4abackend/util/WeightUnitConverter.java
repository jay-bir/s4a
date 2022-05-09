package com.example.s4abackend.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class WeightUnitConverter implements AttributeConverter<WeightUnit, String> {

    @Override
    public String convertToDatabaseColumn(WeightUnit unit){
        return unit.getSymbol();
    }

    @Override
    public WeightUnit convertToEntityAttribute(String symbol){
        return Stream.of(WeightUnit.values())
                .filter(v -> v.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static BigDecimal calculateWeight(BigDecimal weight, WeightUnit currentUnit, WeightUnit targetUnit){
        BigDecimal r_weight;
        if(currentUnit == targetUnit)
            r_weight = weight;
        else if(currentUnit == WeightUnit.KILOGRAM && targetUnit == WeightUnit.POUND)
            r_weight = kgToLb(weight);
        else if(currentUnit == WeightUnit.POUND && targetUnit == WeightUnit.KILOGRAM)
            r_weight = lbToKg(weight);
        else throw new IllegalArgumentException("Wrong units provided in method arguments");
        return  r_weight;
    }

    private static BigDecimal lbToKg(BigDecimal kg){
        BigDecimal w = kg.multiply(new BigDecimal("0.45359237"));

        if(w.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0)
            if(w.remainder(BigDecimal.ONE).compareTo(new BigDecimal("0.5")) < 1)
                w = w.divideToIntegralValue(BigDecimal.ONE).add(new BigDecimal("0.5"));
            else
                w = w.setScale(0, RoundingMode.CEILING);
        return  w;
    }

    private static BigDecimal kgToLb(BigDecimal lb){
        return lb.multiply(new BigDecimal("2.20462262")).setScale(0, RoundingMode.CEILING);
    }

}
