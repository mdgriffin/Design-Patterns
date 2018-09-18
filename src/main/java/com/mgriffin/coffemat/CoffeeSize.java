package com.mgriffin.coffemat;

public enum CoffeeSize{
    SMALL (250),
    MEDIUM (350),
    LARGE (500);

    private double numMillimeters;

    CoffeeSize (double numMillimeters) {
        this.numMillimeters = numMillimeters;
    }

    public double getNumMillimeters () {
        return numMillimeters;
    }
}
