package com.mgriffin.machine;

public interface MachineObservable {

    void addObserver(MachineObserver observer);

    void removeObserver (MachineObserver observer);

}