package com.mgriffin.machine;

import com.mgriffin.order.CoffeeOrder;

public interface CoffeeMachine extends Runnable, MachineObservable {
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
    String getName();
    String getLocation();
}
