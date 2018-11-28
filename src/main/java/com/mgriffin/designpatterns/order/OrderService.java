package com.mgriffin.designpatterns.order;

public interface OrderService extends OrderObservable {
    void addOrder(CoffeeOrder order);
}
