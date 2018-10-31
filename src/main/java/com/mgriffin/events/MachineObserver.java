package com.mgriffin.events;

import com.mgriffin.coffemat.CoffeeMachineImpl;
import com.mgriffin.coffemat.CoffeeOrder;

public interface MachineObserver {
    void coffeeAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void milkAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void condimentsAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
}
