package com.mgriffin.pricing;

import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

public class TimeBasedDiscountFactoryTest {

    @Test
    public void whenTimeIsNoon_discountPricingStrategyApplied () {
        DiscountFactory factory = new TimeBasedDiscountFactory(LocalTime.NOON);
        Discount discount = factory.getDiscount();
        assertEquals(discount.calculate(1), 0.75d, 0.001);
    }

    @Test
    public void whenTimeIsMidnight_regularPricingStrategyApplied () {
        DiscountFactory factory = new TimeBasedDiscountFactory(LocalTime.MIDNIGHT);
        Discount discount = factory.getDiscount();
        assertEquals(discount.calculate(1), 1d, 0.001);
    }

}
