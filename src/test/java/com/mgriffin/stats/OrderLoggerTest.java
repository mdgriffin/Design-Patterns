package com.mgriffin.stats;

import com.mgriffin.order.*;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderLoggerTest {

    // ToDo: Add tests that only one order logger can be created

    @Test
    public void salesTotalIsZero_whenNoOrdersAdded () {
        OrderLogger logger = OrderLogger.INSTANCE;

        assertEquals(0d, logger.getTotalSales(), 0d);
    }

    @Test
    public void addingOrder_increasesTotalSales () {
        OrderLogger logger = OrderLogger.INSTANCE;

        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(new Customer(""))
            .setSize(CoffeeSize.LARGE)
            .setType(CoffeeType.LATTE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();

        assertNotNull(coffeeOrder);

        logger.logOrder(coffeeOrder);

        assertTrue(coffeeOrder.getPrice() > 0);
        assertEquals(3.7d, coffeeOrder.getPrice(), 0.01d);

        assertEquals(3.7d, logger.getTotalSales(), 0.001d);
    }

}
