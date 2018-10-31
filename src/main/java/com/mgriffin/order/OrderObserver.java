package com.mgriffin.order;

public interface OrderObserver {
    void coffeeAdded ();
    void milkAdded ();
    void condimentsAdded ();
    void orderCompleted ();
    void queuePositionChanged (int currentPosition);
}
