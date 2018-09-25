package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;

    @Before
    public void setup () {
        coffeeMachine = new CoffeeMachineImpl();
    }

    @Test
    public void coffeeMachine_isNotNull() {
        assertNotNull(coffeeMachine);
    }

    @Test
    public void machineAvailable_whenNoOrdersAdded () {
        assertTrue(coffeeMachine.available());
    }

    @Test
    public void machineUnavailable_whenOrderAdded () {
        coffeeMachine.start(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order());
        assertFalse(coffeeMachine.available());
    }

}
