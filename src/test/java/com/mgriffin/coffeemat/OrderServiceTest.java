package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Test;

import java.util.Arrays;

public class OrderServiceTest {

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        OrderService orderService = new OrderService(Arrays.asList(coffeeMachine));
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        OrderService orderService = new OrderService(Arrays.asList(coffeeMachine));
        orderService.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order()
        );
    }
}
