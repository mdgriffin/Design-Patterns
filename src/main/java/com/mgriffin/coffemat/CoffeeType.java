package com.mgriffin.coffemat;

public enum CoffeeType implements Billable {
    LATTE (0.007);

    private double pricePerMil;

    CoffeeType (double pricePerMil) {
        this.pricePerMil = pricePerMil;
    }

    @Override
    public double getPrice() {
        return pricePerMil;
    }
}
