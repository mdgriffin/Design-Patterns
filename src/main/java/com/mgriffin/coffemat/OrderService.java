package com.mgriffin.coffemat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private List<CoffeeMachine> coffeeMachines;

    private LinkedList<CoffeeOrder> orders = new LinkedList<>();

    public OrderService (int numMachines) {
        this.coffeeMachines = new ArrayList<>();

        for (int i = 0; i < numMachines; i++) {
            this.coffeeMachines.add(new CoffeeMachine(this));
        }
    }

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

    public void orderCompleted (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        orders.remove(coffeeOrder);

        if (orders.size() > 0) {
            coffeeMachine.start(orders.getFirst());
        }
    }
}
