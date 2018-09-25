package com.mgriffin.coffemat;

public interface CoffeeMachine {
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
}
