package com.mgriffin.coffemat;

public enum CoffeeType implements Billable {
    LATTE (2);

    private double pricePerMil;

    CoffeeType (double pricePerMil) {
        this.pricePerMil = pricePerMil;
    }

    @Override
    public double getPrice() {
        return pricePerMil;
    }
}
