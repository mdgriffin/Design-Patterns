package com.mgriffin.order;

import com.mgriffin.machine.CoffeeMachine;
import com.mgriffin.machine.CoffeeMachineImpl;

import java.util.ArrayList;
import java.util.List;

public class MultiMachineOrderServiceFactory implements OrderServiceFactory {

    private static int NUM_MACHINES = 4;

    @Override
    public OrderService createOrderService() {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 1; i <= NUM_MACHINES; i++) {
            coffeeMachines.add(new CoffeeMachineImpl("Machine " + i, "Canteen"));
        }

        return new OrderServiceImpl(coffeeMachines);
    }

}
