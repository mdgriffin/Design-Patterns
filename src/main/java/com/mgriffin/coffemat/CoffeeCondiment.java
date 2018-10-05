package com.mgriffin.coffemat;

public enum CoffeeCondiment implements Billable {
    CREAM ("Cream", 0.2d),
    MILK("Milk", 0.1d),
    SUGAR("Sugar", 0d);

    private String displayName;
    private double price;

    CoffeeCondiment (String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public double getPrice () {
        return price;
    }
}
