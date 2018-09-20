package com.mgriffin.coffemat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderService {

    private List<CoffeeMachine> coffeeMachines;

    private List<CoffeeOrder> orders = new LinkedList<>();

    public OrderService (int numMachines) {
        this.coffeeMachines = new ArrayList<>();

        for (int i = 0; i < numMachines; i++) {
            this.coffeeMachines.add(new CoffeeMachine(this));
        }
    }

    public void addOrder(CoffeeOrder order) {
        orders.add(order);
    }

    public void orderCompleted (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {

    }
}
