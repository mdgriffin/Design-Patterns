package com.mgriffin.designpatterns.order;

import com.mgriffin.designpatterns.machine.CoffeeMachineImpl;

import java.util.Arrays;

public class SingleMachineOrderServiceFactory implements OrderServiceFactory {

    @Override
    public OrderService createOrderService() {
        return new OrderServiceImpl(Arrays.asList(new CoffeeMachineImpl("Machine 1", "Canteen")));
    }

}
