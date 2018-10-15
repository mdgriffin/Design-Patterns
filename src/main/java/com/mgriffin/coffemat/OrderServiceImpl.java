package com.mgriffin.coffemat;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService, CoffeeOrderObserver {

    private List<CoffeeMachine> coffeeMachines;

    private LinkedList<CoffeeOrder> orders = new LinkedList<>();

    public OrderServiceImpl(List<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;

        coffeeMachines.forEach(coffeeMachine -> {
            Thread machineThread = new Thread(coffeeMachine);
            machineThread.start();
            coffeeMachine.addObserver(this);
        });
    }

    @Override
    public void addOrder(CoffeeOrder order) {
        orders.add(order);

        Optional<CoffeeMachine> availableMachine = getAvailableCoffeeMachine();

        if (availableMachine.isPresent()) {
            availableMachine.get().start(order);
        }
    }

    private Optional<CoffeeMachine> getAvailableCoffeeMachine () {
        return coffeeMachines.stream().filter(coffeeMachine -> coffeeMachine.available()).findFirst();
    }

    @Override
    public void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        orders.remove(coffeeOrder);

        if (orders.size() > 0) {
            coffeeMachine.start(orders.getFirst());
        }
    }

    @Override
    public String toString () {
        return "Number of orders: " + orders.size();
    }

}
