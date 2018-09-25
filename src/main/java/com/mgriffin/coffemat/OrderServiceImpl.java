package com.mgriffin.coffemat;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private List<CoffeeMachineImpl> coffeeMachines;

    private LinkedList<CoffeeOrder> orders = new LinkedList<>();

    public OrderServiceImpl(int numMachines) {
        this.coffeeMachines = new ArrayList<>();

        for (int i = 0; i < numMachines; i++) {
            this.coffeeMachines.add(new CoffeeMachineImpl(this));
        }
    }

    public void addOrder(CoffeeOrder order) {
        orders.add(order);

        Optional<CoffeeMachineImpl> availableMachine = getAvailableCoffeeMachine();

        if (availableMachine.isPresent()) {
            availableMachine.get().start(order);
        }
    }


    private Optional<CoffeeMachineImpl> getAvailableCoffeeMachine () {
        return coffeeMachines.stream().filter(coffeeMachine -> coffeeMachine.available()).findFirst();
    }

    public void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        orders.remove(coffeeOrder);

        if (orders.size() > 0) {
            coffeeMachine.start(orders.getFirst());
        }
    }
}
