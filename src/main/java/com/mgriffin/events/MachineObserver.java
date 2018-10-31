package com.mgriffin.events;

import com.mgriffin.coffemat.CoffeeMachineImpl;
import com.mgriffin.coffemat.CoffeeOrder;

public interface MachineObserver {
    void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
}
