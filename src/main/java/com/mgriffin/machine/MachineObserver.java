package com.mgriffin.machine;

import com.mgriffin.order.CoffeeOrder;

public interface MachineObserver {
    void coffeeAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void milkAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void condimentsAdded (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
    void orderCompleted (CoffeeMachineImpl coffeeMachine, CoffeeOrder coffeeOrder);
}
