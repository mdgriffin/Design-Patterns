package com.mgriffin.coffemat;

public interface CoffeeMachine extends OrderObservable{
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
}
