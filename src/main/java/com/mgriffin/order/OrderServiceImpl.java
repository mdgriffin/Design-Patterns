package com.mgriffin.order;

import com.mgriffin.machine.CoffeeMachine;
import com.mgriffin.machine.CoffeeMachineImpl;
import com.mgriffin.machine.MachineObserver;
import com.mgriffin.stats.OrderLogger;

import java.util.*;

class OrderServiceImpl implements OrderService, MachineObserver, OrderObservable {

    private List<CoffeeMachine> coffeeMachines;
    private LinkedList<CoffeeOrder> orders = new LinkedList<>();
    private Map<CoffeeOrder, List<OrderObserver>> observers = new HashMap();
    private OrderLogger orderLogger = OrderLogger.INSTANCE;

    public OrderServiceImpl(List<CoffeeMachine> coffeeMachines) {
        this.coffeeMachines = coffeeMachines;

        coffeeMachines.forEach(coffeeMachine -> {
            coffeeMachine.addObserver(this);
        });
    }

    @Override
    public void addOrder(CoffeeOrder order) {
        orders.add(order);
        notifyQueuePosition();
        orderLogger.logOrder(order);

        Optional<CoffeeMachine> availableMachine = getAvailableCoffeeMachine();

        availableMachine.ifPresent(machine -> machine.start(order));
    }

    private Optional<CoffeeMachine> getAvailableCoffeeMachine () {
        return coffeeMachines.stream().filter(coffeeMachine -> coffeeMachine.available()).findFirst();
    }

    @Override
    public void coffeeAdded(CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.coffeeAdded());
        }
    }

    @Override
    public void milkAdded(CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.milkAdded());
        }
    }

    @Override
    public void condimentsAdded(CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.condimentsAdded());
        }
    }

    @Override
    public void orderCompleted (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        orders.remove(coffeeOrder);

        if (observers.get(coffeeOrder) != null) {
            observers.get(coffeeOrder).forEach(observer -> observer.orderCompleted(coffeeMachine.getName(), coffeeMachine.getLocation()));
        }

        if (orders.size() > 0) {
            notifyQueuePosition();
            coffeeMachine.start(orders.get(0));
        }
    }

    public void notifyQueuePosition () {
        observers.forEach((order, observerList) -> {
            observerList.forEach(observer -> {
                int queuePosition = orders.indexOf(order);
                if (queuePosition != -1 && queuePosition != 0) {
                    observer.queuePositionChanged(queuePosition);
                }
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
