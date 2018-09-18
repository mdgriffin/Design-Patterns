package com.mgriffin.coffemat;

import java.util.LinkedList;
import java.util.List;

public class CoffeeMachine {

    private List<CoffeeOrder> orders = new LinkedList<>();

    public CoffeeMachine() {

    }

    public void addOrder(CoffeeOrder order) {
        orders.add(order);
    }

}