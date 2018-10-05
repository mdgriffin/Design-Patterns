package com.mgriffin.pricing;

public class RegularPrice implements Discount {

    @Override
    public double calculate(double amount) {
        return amount;
    }

}
