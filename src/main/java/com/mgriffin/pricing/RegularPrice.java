package com.mgriffin.pricing;

class RegularPrice implements Discount {

    @Override
    public double calculate(double amount) {
        return amount;
    }

}
