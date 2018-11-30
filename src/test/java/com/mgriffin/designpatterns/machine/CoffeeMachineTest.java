package com.mgriffin.designpatterns.machine;

import com.mgriffin.designpatterns.order.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;

    @Before
    public void setup () {
        coffeeMachine = new CoffeeMachineImpl("Machine 1", "Canteen");
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

    @Test
    public void coffeeOrderStateChanges_whenMachineAvailable () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order();

        assertEquals(OrderStates.WAITING, coffeeOrder.getOrderState());
        coffeeMachine.start(coffeeOrder);
    }

    @Test
    public void observersRegisteredWithCoffeeMachine_invoked () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order();
        MachineObserver observer = mock(MachineObserver.class);

        coffeeMachine.addObserver(observer);
        coffeeMachine.start(coffeeOrder);

        verify(observer, times(1)).coffeeAdded(any(CoffeeMachine.class), any(CoffeeOrder.class));
    }

}
