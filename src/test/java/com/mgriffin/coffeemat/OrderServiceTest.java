package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    private static final int NUM_MACHINES = 4;

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        OrderService orderService = new OrderServiceImpl(coffeeMachines);
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        OrderService orderService = new OrderServiceImpl(coffeeMachines);
        orderService.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order()
        );
    }
}
