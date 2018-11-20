package com.mgriffin.machine;

import com.mgriffin.order.CoffeeOrder;

public interface MachineObserver {
    void coffeeAdded (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder);
    void milkAdded (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder);
    void condimentsAdded (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder);
    void orderCompleted (CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder);
}
