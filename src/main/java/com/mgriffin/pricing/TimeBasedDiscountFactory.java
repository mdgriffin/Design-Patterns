package com.mgriffin.pricing;

import java.time.LocalTime;
import java.util.Date;

public class TimeBasedDiscountFactory implements DiscountFactory {

    private LocalTime currentTime;

    public TimeBasedDiscountFactory () {
        this.currentTime = LocalTime.now();
    }

    public TimeBasedDiscountFactory (LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public Discount getDiscount() {
        Boolean isBeforeNoon = (currentTime.isAfter(LocalTime.parse( "08:59:59")) && currentTime.isBefore(LocalTime.parse("12:00:01")));

        return isBeforeNoon ? new MorningMadnessPrice() : new RegularPrice();
    }
}
