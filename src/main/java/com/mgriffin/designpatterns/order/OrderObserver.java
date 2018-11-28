package com.mgriffin.designpatterns.order;

public interface OrderObserver {
    void coffeeAdded ();
    void milkAdded ();
    void condimentsAdded ();
    void orderCompleted (String machineName, String machineLocation);
    void queuePositionChanged (int currentPosition);
}
