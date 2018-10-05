package com.mgriffin.pricing;

public class BlackFridayPrice implements Discount {

    @Override
    public double calculate(double amount) {
        return amount * 0.8;
    }

}
