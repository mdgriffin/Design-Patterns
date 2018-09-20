package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Test;

import java.util.Arrays;

public class OrderServiceTest {

    private static final int NUM_MACHINES = 4;

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        OrderService orderService = new OrderService(NUM_MACHINES);
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        OrderService orderService = new OrderService(NUM_MACHINES);
        orderService.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order()
        );
    }
}
