package com.mgriffin.coffemat;

public interface CoffeeOrderObserver {
    void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
}
