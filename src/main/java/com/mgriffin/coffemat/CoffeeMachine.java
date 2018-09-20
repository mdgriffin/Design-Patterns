package com.mgriffin.coffemat;

import java.util.LinkedList;
import java.util.List;

public class CoffeeMachine {

    OrderService orderService;

    CoffeeOrder coffeeOrder;

    public CoffeeMachine(OrderService orderService) {
        this.orderService = orderService;
    }

    public boolean available () {
        return coffeeOrder == null || coffeeOrder.getOrderState() == OrderStates.COMPLETED;
    }

    public void start (CoffeeOrder coffeeOrder) {
        if (available()) {
            this.coffeeOrder = coffeeOrder;
        }
    }

    private void completed (CoffeeOrder coffeeOrder) {
        this.orderService.orderCompleted(this, coffeeOrder);
    }

}