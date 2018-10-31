package com.mgriffin.coffemat;

import com.mgriffin.events.MachineObserver;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineImpl implements CoffeeMachine {

    CoffeeOrder coffeeOrder;

    List<MachineObserver> observers = new ArrayList();

    private Thread machineThread;

    public CoffeeMachineImpl() {}

    @Override
    public boolean available () {
        return (coffeeOrder == null || coffeeOrder.getOrderState() == OrderStates.COMPLETED);
    }

    @Override
    public void start (CoffeeOrder coffeeOrder) {
        if (available()) {
            this.coffeeOrder = coffeeOrder;

            if (machineThread != null && machineThread.isAlive()) {
                machineThread.interrupt();
            }

            machineThread = new Thread(this);
            machineThread.start();
        }
    }

    private void addCoffee () {
        coffeeOrder.addCoffee();
        observers.forEach(observer -> observer.coffeeAdded(this, coffeeOrder));
    }

    private void addMilk () {
        coffeeOrder.addMilk();
        observers.forEach(observer -> observer.milkAdded(this, coffeeOrder));
    }

    private void addCondiments() {
        coffeeOrder.addCondiments();
        observers.forEach(observer -> observer.condimentsAdded(this, coffeeOrder));
    }

    private void completeOrder () {
        coffeeOrder.completeOrder();
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
                addCoffee();
                Thread.sleep(1000);
                addMilk();
                Thread.sleep(1000);
                addCondiments();
                Thread.sleep(1000);
                completeOrder();
            } catch (InterruptedException exc) {
                System.out.println("Exception");
                System.out.println(exc);
            }
        }
    }
}