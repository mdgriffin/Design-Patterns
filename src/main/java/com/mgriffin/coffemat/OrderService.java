package com.mgriffin.coffemat;

public interface OrderService {
    void addOrder(CoffeeOrder order);
    void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
}
