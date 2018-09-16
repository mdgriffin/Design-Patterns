package com.mgriffin.simplestateengine;

import java.util.EnumSet;
import java.util.Set;

public class StateEngine {

    private EnumSet states;

    private EnumSet events;

    private Set<Enum> transitions;

    public StateEngine (EnumSet states, EnumSet events) {
        this.states = states;
        this.events = events;
    }

    public void addTransition (Enum startState, Enum endState, Enum event) {

    }
}
