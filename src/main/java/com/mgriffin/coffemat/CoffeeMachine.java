package com.mgriffin.coffemat;

public interface CoffeeMachine extends OrderObservable, Runnable{
    boolean available ();
    void start (CoffeeOrder coffeeOrder);
    void stop ();
}
