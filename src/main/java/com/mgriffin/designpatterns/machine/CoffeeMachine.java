package com.mgriffin.designpatterns.machine;

import com.mgriffin.designpatterns.order.CoffeeOrder;

public interface CoffeeMachine extends Runnable, MachineObservable {
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
    String getName();
    String getLocation();
}
