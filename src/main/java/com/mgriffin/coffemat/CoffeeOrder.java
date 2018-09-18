package com.mgriffin.coffemat;

import com.mgriffin.simplestateengine.StateEngine;

import java.util.EnumSet;

public class CoffeeOrder {

    private StateEngine state;

    private Customer customer;

    private double cost;

    public CoffeeOrder (Customer customer) {
        this.customer = customer;

        this.state = new StateEngine.StateEngineBuilder()
            .setStates(EnumSet.allOf(OrderStates.class))
            .setEvents(EnumSet.allOf(OrderEvents.class))
            .setStartState(OrderStates.WAITING)
            .addTransition(OrderStates.WAITING, OrderStates.COFFEE, OrderEvents.SELECT_COFFEE)
            .addTransition(OrderStates.COFFEE, OrderStates.MILK, OrderEvents.SELECT_MILK)
            .addTransition(OrderStates.MILK, OrderStates.CONDIMENTS, OrderEvents.SELECT_CONDIMENTS)
            .addTransition(OrderStates.CONDIMENTS, OrderStates.PAID, OrderEvents.PAY)
            .addTransition(OrderStates.PAID, OrderStates.MADE, OrderEvents.MAKE)
            .addTransition(OrderStates.MADE, OrderStates.COMPLETED, OrderEvents.DELIVER)
            .build();
    }

    public Enum getOrderState () {
        return this.state.getCurrentState();
    }
}
