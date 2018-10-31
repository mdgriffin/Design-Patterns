package com.mgriffin.events;

public interface OrderObserver {
    void coffeeAdded ();
    void milkAdded ();
    void condimentsAdded ();
    void orderCompleted ();
}
