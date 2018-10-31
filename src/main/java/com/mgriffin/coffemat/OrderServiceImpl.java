package com.mgriffin.coffemat;

import com.mgriffin.events.MachineObserver;
import com.mgriffin.events.OrderObservable;
import com.mgriffin.events.OrderObserver;

import java.util.*;

public class OrderServiceImpl implements OrderService, MachineObserver, OrderObservable {

    private List<CoffeeMachine> coffeeMachines;

    private LinkedList<CoffeeOrder> orders = new LinkedList<>();

    private Map<CoffeeOrder, List<OrderObserver>> observers = new HashMap();

    public OrderServiceImpl(List<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;

        coffeeMachines.forEach(coffeeMachine -> {
            coffeeMachine.addObserver(this);
        });
    }

    @Override
    public void addOrder(CoffeeOrder order) {
        orders.add(order);

        Optional<CoffeeMachine> availableMachine = getAvailableCoffeeMachine();

        if (availableMachine.isPresent()) {
            notifyQueuePosition();
            availableMachine.get().start(order);
        }
    }

    private Optional<CoffeeMachine> getAvailableCoffeeMachine () {
        return coffeeMachines.stream().filter(coffeeMachine -> coffeeMachine.available()).findFirst();
    }

    @Override
    public void coffeeAdded(CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.coffeeAdded());
        }
    }

    @Override
    public void milkAdded(CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.milkAdded());
        }
    }

    @Override
    public void condimentsAdded(CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.condimentsAdded());
        }
    }

    @Override
    public void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder) {
        orders.remove(coffeeOrder);

        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.orderCompleted());
        }

        if (orders.size() > 0) {
            notifyQueuePosition();
            coffeeMachine.start(orders.getFirst());
        }
    }

    public void notifyQueuePosition () {
        observers.forEach((order, observerList) -> {
            observerList.forEach(observer -> {
                observer.queuePositionChanged(orders.indexOf(order));
            });
        });
    }

    @Override
    public String toString () {
        return "Number of orders: " + orders.size();
    }

    @Override
    public void addObserver(CoffeeOrder order, OrderObserver observer) {
        if (observers.get(order) == null) {
            observers.put(order, new ArrayList<OrderObserver>());
        }
        observers.get(order).add(observer);
    }

    @Override
    public void removeObserver(CoffeeOrder order, OrderObserver observer) {
        if (observers.get(order) != null) {
            observers.get(order).removeIf(listOrder -> listOrder.equals(order));
        }
    }
}
