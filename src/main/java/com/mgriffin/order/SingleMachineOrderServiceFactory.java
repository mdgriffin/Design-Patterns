package com.mgriffin.order;

import com.mgriffin.machine.CoffeeMachine;
import com.mgriffin.machine.CoffeeMachineImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleMachineOrderServiceFactory implements OrderServiceFactory {

    @Override
    public OrderService createOrderService() {
        return new OrderServiceImpl(Arrays.asList(new CoffeeMachineImpl()));
    }

}
