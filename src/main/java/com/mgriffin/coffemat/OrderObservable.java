package com.mgriffin.coffemat;

public interface OrderObservable {

    void addObserver(CoffeeOrderObserver observer);

    void removeObserver (CoffeeOrderObserver observer);

}
