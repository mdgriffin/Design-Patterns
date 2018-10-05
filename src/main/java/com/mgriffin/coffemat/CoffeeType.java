package com.mgriffin.coffemat;

public enum CoffeeType implements Billable {
    LATTE ("Latte", 0.007),
    ICED_LATTE("Iced Latte", 0.0011),
    AMERICANO ("Americano", 0.0010),
    FLAT_WHITE("Flat White", 0.008),
    CAPPUCINO("Cappucino", 0.009),
    MOCHA("Mocha", 0.007),
    ESPRESSO ("Espresso", 0.007);

    private String displayName;
    private double pricePerMil;

    CoffeeType (String displayName, double pricePerMil) {
        this.displayName = displayName;
        this.pricePerMil = pricePerMil;
    }

    public String getDisplayName () {
        return this.displayName;
    }

    @Override
    public double getPrice() {
        return pricePerMil;
    }
}
