package com.mgriffin.coffemat;

public enum CoffeeSize{
    SMALL (2),
    MEDIUM (2.5),
    LARGE (3);

    private double numMillimeters;

    CoffeeSize (double numMillimeters) {
        this.numMillimeters = numMillimeters;
    }

    public double getNumMillimeters () {
        return getNumMillimeters();
    }
}
