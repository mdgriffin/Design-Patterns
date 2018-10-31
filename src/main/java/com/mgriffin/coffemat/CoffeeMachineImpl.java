package com.mgriffin.coffemat;

import com.mgriffin.events.MachineObserver;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineImpl implements CoffeeMachine {

    CoffeeOrder coffeeOrder;

    List<MachineObserver> observers = new ArrayList();

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
    public void addObserver(MachineObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver (MachineObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void run() {
        if (!available()) {
            try {
                coffeeOrder.addCoffee();
                Thread.sleep(1000);
                coffeeOrder.addMilk();
                Thread.sleep(1000);
                coffeeOrder.addCondiments();
                Thread.sleep(1000);
                coffeeOrder.completeOrder();
                completed(coffeeOrder);
            } catch (InterruptedException exc) {
                System.out.println("Exception");
                System.out.println(exc);
                running = false;
            }
        }
}
}