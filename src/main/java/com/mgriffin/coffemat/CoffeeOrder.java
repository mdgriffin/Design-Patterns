package com.mgriffin.coffemat;

import com.mgriffin.simplestateengine.StateEngine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CoffeeOrder implements Billable {

    private StateEngine state;

    private Customer customer;

    private CoffeeType coffeeType;

    private CoffeeSize coffeeSize;

    private List<CoffeeCondiment> condiments;

    private  CoffeeOrder() {}

    private CoffeeOrder (Customer customer, CoffeeType coffeeType, CoffeeSize coffeeSize, List<CoffeeCondiment> condiments) {
        this.customer = customer;
        this.coffeeType = coffeeType;
        this.coffeeSize = coffeeSize;
        this.condiments = condiments;

        this.state = new StateEngine.StateEngineBuilder()
            .setStates(EnumSet.allOf(OrderStates.class))
            .setEvents(EnumSet.allOf(OrderEvents.class))
            .setStartState(OrderStates.WAITING)
            .addTransition(OrderStates.WAITING, OrderStates.COFFEE_ADDED, OrderEvents.ADD_COFFEE)
            .addTransition(OrderStates.COFFEE_ADDED, OrderStates.MILK_ADDED, OrderEvents.ADD_MILK)
            .addTransition(OrderStates.MILK_ADDED, OrderStates.CONDIMENTS_ADDED, OrderEvents.ADD_CONDIMENTS)
            .addTransition(OrderStates.CONDIMENTS_ADDED, OrderStates.COMPLETED, OrderEvents.COMPLETE_ORDER)
            .build();
    }

    public Enum getOrderState () {
        return this.state.getCurrentState();
    }

    public double getPrice () {
        return (coffeeType.getPrice() * coffeeSize.getNumMillimeters()) + condiments
            .stream().mapToDouble(condiment -> condiment.getPrice()).sum();
    }

    public static class CoffeeOrderBuilder {

        private Customer customer;

        private CoffeeType coffeeType;

        private CoffeeSize coffeeSize;

        private List<CoffeeCondiment> condiments;

        public CoffeeOrderBuilder () {
            condiments = new ArrayList<>();
        }

        public CoffeeOrderBuilder setCustomer (Customer customer) {
            this.customer = customer;
            return this;
        }

        public CoffeeOrderBuilder setType (CoffeeType coffeeType) {
            this.coffeeType = coffeeType;
            return this;
        }

        public CoffeeOrderBuilder setSize (CoffeeSize coffeeSize) {
            this.coffeeSize = coffeeSize;
            return this;
        }

        public CoffeeOrderBuilder addCondiment (CoffeeCondiment condiment) {
            this.condiments.add(condiment);
            return this;
        }

        public CoffeeOrder order () {
            if (customer == null || coffeeType == null || coffeeSize == null) {
                throw new IllegalArgumentException();
            }
            return new CoffeeOrder (customer, coffeeType, coffeeSize, condiments);
        }
    }

}
