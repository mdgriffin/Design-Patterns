package com.mgriffin.order;

public enum CoffeeSize{
    SMALL ("Small", 250),
    MEDIUM ("Medium", 350),
    LARGE ("Large", 500);

    private String displayName;
    private double numMillimeters;

    CoffeeSize (String displayName, double numMillimeters) {
        this.displayName = displayName;
        this.numMillimeters = numMillimeters;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getNumMillimeters () {
        return numMillimeters;
    }
}
