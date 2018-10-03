package com.mgriffin.driver;

import com.mgriffin.coffemat.*;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDriver {

    public static void main (String[] args) {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        CoffeeOrder order = new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();

        orderService.addOrder(order);

        System.out.println(orderService.toString());


    }

}
