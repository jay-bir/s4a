package com.example.s4abackend.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class WeightUnitConverterTest {

    @Test
    public void lbToKgRemainderEqualZeroTest(){
        Assertions.assertEquals(
                0.0,
                WeightUnitConverter.calculateWeight(BigDecimal.ZERO, WeightUnit.POUND, WeightUnit.KILOGRAM).doubleValue()
        );
    }

    @Test
    public void lbToKgRemainderBetweenZeroAndHalfTest(){
        Assertions.assertEquals(
                0.5,
                WeightUnitConverter.calculateWeight(BigDecimal.ONE, WeightUnit.POUND, WeightUnit.KILOGRAM).doubleValue()
        );
    }

    @Test
    public void lbToKgRemainderGreaterThanHalfTest(){
        Assertions.assertEquals(
                1.0,
                WeightUnitConverter.calculateWeight(new BigDecimal("2"), WeightUnit.POUND, WeightUnit.KILOGRAM).doubleValue()
        );
    }

    @Test
    public void kgToLbRoundingUpTest(){
        Assertions.assertEquals(
                3.0,
                WeightUnitConverter.calculateWeight(BigDecimal.ONE, WeightUnit.KILOGRAM, WeightUnit.POUND).doubleValue()
        );
    }
}

