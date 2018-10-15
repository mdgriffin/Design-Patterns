package com.mgriffin.coffemat;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineImpl implements CoffeeMachine, OrderObservable  {

    CoffeeOrder coffeeOrder;

    List<CoffeeOrderObserver> observers = new ArrayList();

    private boolean running;

    public CoffeeMachineImpl() {
        running = true;
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

    @Override
    public void stop() {
        running = false;
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

    @Override
    public void run() {
        while (running) {
            if (!available()) {
                try {
                    coffeeOrder.addCoffee();
                    Thread.sleep(1000);
                    coffeeOrder.addMilk();
                    Thread.sleep(1000);
                    coffeeOrder.addCondiments();
                    Thread.sleep(1000);
                    coffeeOrder.completeOrder();
                } catch (InterruptedException exc) {
                    running = false;
                }
            }
        }
}
}