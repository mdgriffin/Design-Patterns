package com.mgriffin.coffemat;

import java.util.LinkedList;
import java.util.List;

public class OrderService {

    private List<CoffeeMachine> coffeeMachines;

    private List<CoffeeOrder> orders = new LinkedList<>();

    public OrderService (List<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;
    }

    public void addOrder(CoffeeOrder order) {
        orders.add(order);
    }
}
