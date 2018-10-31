package com.mgriffin.order;

import com.mgriffin.machine.CoffeeMachine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private static final int NUM_MACHINES = 4;

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        //List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        OrderService orderService = new OrderServiceImpl();
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        //List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        OrderService orderService = new OrderServiceImpl();
        orderService.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order()
        );
    }

    @Test
    public void whenAddingOrder_orderIsStarted_whenMachineAvailable () {
        CoffeeMachine mockCoffeeMachine = mock(CoffeeMachine.class);

        when(mockCoffeeMachine.available()).thenReturn(true);

        //List<CoffeeMachine> coffeeMachines = Arrays.asList(mockCoffeeMachine);
        OrderService orderService = new OrderServiceImpl();
        orderService.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order()
        );

        verify(mockCoffeeMachine).start(any(CoffeeOrder.class));
    }

}
