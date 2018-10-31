package com.mgriffin.coffemat;

import com.mgriffin.events.MachineObservable;

public interface CoffeeMachine extends Runnable, MachineObservable {
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
}
