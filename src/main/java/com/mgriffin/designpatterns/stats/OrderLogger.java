package com.mgriffin.designpatterns.stats;

import com.mgriffin.designpatterns.order.CoffeeOrder;

import java.util.ArrayList;
import java.util.List;

public enum OrderLogger {
    INSTANCE;

    private List<CoffeeOrder> orders = new ArrayList<>();

    OrderLogger () {}

    public void logOrder (CoffeeOrder coffeeOrder) {
        this.orders.add(coffeeOrder);
    }

    public double getTotalSales () {
        return this.orders.stream().mapToDouble(order -> {
            return order.getPrice();
        }).sum();
    }

}
