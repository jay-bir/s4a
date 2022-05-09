package com.example.s4abackend.util;

public enum WeightUnit {
    KILOGRAM("kg"),
    POUND("lb");

    private String symbol;

    private WeightUnit(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }
}
