package com.mgriffin.order;

import com.mgriffin.machine.CoffeeMachine;
import com.mgriffin.machine.CoffeeMachineImpl;

import java.util.ArrayList;
import java.util.List;

public class SingleMachineOrderService implements OrderFactory {

    @Override
    public OrderService getOrderService() {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        coffeeMachines.add(new CoffeeMachineImpl());

        return new OrderServiceImpl(coffeeMachines);
    }

}
