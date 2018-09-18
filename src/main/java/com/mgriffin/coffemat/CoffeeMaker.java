package com.mgriffin.coffemat;

import java.util.LinkedList;
import java.util.List;

public class CoffeeMaker {

    private List<CoffeeOrder> orders = new LinkedList<>();

    public CoffeeMaker() {

    }

    public void addOrder(CoffeeOrder order) {
        orders.add(order);
    }

}