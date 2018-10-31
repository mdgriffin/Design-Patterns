package com.mgriffin.stateengine;

public interface StateChangeObservable {

    void addObserver (StateChangeObserver observer);

    void addObserver (Enum state, StateChangeObserver observer);

    void removeObserver (StateChangeObserver observer);

    void removeObserver (Enum state, StateChangeObserver observer);
}
