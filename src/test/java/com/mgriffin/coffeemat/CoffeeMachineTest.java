package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Test;

public class CoffeeMachineTest {

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        CoffeeMachine coffeeMaker = new CoffeeMachine();
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        CoffeeMachine coffeeMaker = new CoffeeMachine();
        coffeeMaker.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order()
        );
    }
}
