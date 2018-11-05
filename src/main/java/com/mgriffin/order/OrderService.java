package com.mgriffin.order;

public interface OrderService extends OrderObservable {
    void addOrder(CoffeeOrder order);
}
