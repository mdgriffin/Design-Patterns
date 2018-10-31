package com.mgriffin.driver;

import com.mgriffin.coffemat.*;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.events.OrderObserver;

import java.util.*;

public class ConsoleDriver {

    public static void main (String[] args) {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder();

        CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

        System.out.println("Your order: \n" + coffeeOrder.toString());

        ((OrderServiceImpl) orderService).addObserver(coffeeOrder, new OrderObserver() {
            @Override
            public void coffeeAdded() {
                System.out.println("Coffee Added");
            }

            @Override
            public void milkAdded() {
                System.out.println("Milk Added");
            }

            @Override
            public void condimentsAdded() {
                System.out.println("Condiments Added");
            }

            @Override
            public void orderCompleted() {
                System.out.println("Order Completed");
                System.exit(0);
            }

            @Override
            public void queuePositionChanged(int currentPosition) {
                System.out.println("Current Position in Queue: " + currentPosition);
            }
        });

        orderService.addOrder(coffeeOrder);
    }

}
