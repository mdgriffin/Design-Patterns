package com.mgriffin.designpatterns.pricing;

public class RegularPrice implements Discount {

    @Override
    public double calculate(double amount) {
        return amount;
    }

}
