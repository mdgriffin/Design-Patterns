package com.mgriffin.pricing;

import java.time.LocalTime;
import java.util.Date;

public class TimeBasedDiscountFactory implements DiscountFactory {

    @Override
    public Discount getDiscount() {
        LocalTime currentTime = LocalTime.now();
        Boolean isBeforeNoon = (currentTime.isAfter(LocalTime.parse( "08:59:59")) && currentTime.isBefore(LocalTime.parse("12:00:01")));

        return isBeforeNoon ? new MorningMadnessPrice() : new RegularPrice();
    }
}
