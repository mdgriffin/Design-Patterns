package com.mgriffin.designpatterns.stats;

import com.mgriffin.designpatterns.order.*;
import com.mgriffin.designpatterns.pricing.RegularPrice;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderLoggerTest {

    @Test
    public void addingOrder_increasesTotalSales () {
        OrderLogger logger = OrderLogger.INSTANCE;

        double initialTotalSales = logger.getTotalSales();

        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(new Customer(""))
            .setSize(CoffeeSize.LARGE)
            .setType(CoffeeType.LATTE)
            .setDiscount(new RegularPrice())
            .addCondiment(CoffeeCondiment.CREAM)
            .order();

        assertNotNull(coffeeOrder);

        logger.logOrder(coffeeOrder);

        assertTrue(coffeeOrder.getPrice() > 0);

        double expectedTotalSales = initialTotalSales + coffeeOrder.getPrice();

        assertTrue(expectedTotalSales > initialTotalSales);
        assertEquals(expectedTotalSales, logger.getTotalSales(), 0.01d);
    }

}
