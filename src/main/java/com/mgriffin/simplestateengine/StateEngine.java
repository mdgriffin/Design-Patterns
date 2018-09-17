package com.mgriffin.simplestateengine;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StateEngine {

    private EnumSet states;

    private EnumSet events;

    private Enum currentState;

    private Map<Enum, Map<Enum, Enum>> transitions;

    public StateEngine (EnumSet states, EnumSet events, Enum startState) {
        if (!states.contains(startState)) {
            throw new IllegalArgumentException("Start State must be one of the defined states");
        }

        this.states = states;
        this.events = events;
        this.currentState = startState;
        transitions = new HashMap();
        Iterator<Enum> statesIterator = states.iterator();

        while(statesIterator.hasNext()) {
            transitions.put(statesIterator.next(), new HashMap());
        }
    }

    public void addTransition (Enum startState, Enum endState, Enum event) {
        transitions.get(startState).put(event, endState);
    }

    public Enum getCurrentState () {
        return currentState;
    }

}
