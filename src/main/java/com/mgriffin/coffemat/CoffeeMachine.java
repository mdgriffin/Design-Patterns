package com.mgriffin.coffemat;

import com.mgriffin.simplestateengine.StateChangeObserver;

public interface CoffeeMachine{
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
    void addObserver (CoffeeOrderObserver observer);
    void removeObserver (CoffeeOrderObserver observer);
}
