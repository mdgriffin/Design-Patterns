package com.mgriffin.coffemat;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineImpl implements CoffeeMachine, OrderObservable  {

    CoffeeOrder coffeeOrder;

    List<CoffeeOrderObserver> observers = new ArrayList();

    public CoffeeMachineImpl() {
    }

    @Override
    public boolean available () {
        return coffeeOrder == null || coffeeOrder.getOrderState() == OrderStates.COMPLETED;
    }

    @Override
    public void start (CoffeeOrder coffeeOrder) {
        if (available()) {
            this.coffeeOrder = coffeeOrder;
            this.coffeeOrder.addCoffee();
        }
    }

    private void completed (CoffeeOrder coffeeOrder) {
        observers.forEach(observer -> observer.orderCompleted(this, coffeeOrder));
    }


    @Override
    public void addObserver(CoffeeOrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver (CoffeeOrderObserver observer) {
        observers.remove(observer);
    }
}