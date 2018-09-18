package com.mgriffin.coffemat;

public enum CoffeeCondiment implements Billable {
    CREAM (2d);

    private double price;

    CoffeeCondiment (double price) {
        this.price = price;
    }

    @Override
    public double getPrice () {
        return price;
    }
}
