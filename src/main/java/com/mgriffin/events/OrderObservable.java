package com.mgriffin.events;

import com.mgriffin.coffemat.CoffeeOrder;

public interface OrderObservable {

    void addObserver(CoffeeOrder order, OrderObserver observer);

    void removeObserver (CoffeeOrder order, OrderObserver observer);

}