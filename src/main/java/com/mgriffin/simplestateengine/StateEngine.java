package com.mgriffin.simplestateengine;

public class StateEngine {

    private Enum states;

    private Enum events;

    public StateEngine (Enum states, Enum events) {
        this.states = states;
        this.events = events;
    }

    public void addTransition (Enum startState, Enum endState, Enum event) {

    }
}
