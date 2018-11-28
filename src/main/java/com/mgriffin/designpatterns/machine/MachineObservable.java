package com.mgriffin.designpatterns.machine;

public interface MachineObservable {

    void addObserver(MachineObserver observer);

    void removeObserver (MachineObserver observer);

}