package com.mgriffin.coffemat;

import com.mgriffin.simplestateengine.StateChangeObserver;

public interface CoffeeMachine extends OrderObservable {
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
}
