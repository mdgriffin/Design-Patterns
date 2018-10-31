package com.mgriffin.events;

public interface MachineObservable {

    void addObserver(MachineObserver observer);

    void removeObserver (MachineObserver observer);

}