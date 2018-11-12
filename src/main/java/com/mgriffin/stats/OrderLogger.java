package com.mgriffin.stats;

import com.mgriffin.order.CoffeeOrder;

import java.util.ArrayList;
import java.util.List;

public enum OrderLogger {
    INSTANCE;

    private List<CoffeeOrder> orders = new ArrayList<>();

    private static OrderLogger instance = null;

    OrderLogger () {

    }

    public void logOrder (CoffeeOrder coffeeOrder) {
        this.orders.add(coffeeOrder);
    }

    public double getTotalSales () {
        return this.orders.stream().mapToDouble(order -> {
            return order.getPrice();
        }).sum();
    }

}
