package com.mgriffin.simplestateengine;

import javax.swing.plaf.nimbus.State;
import java.util.*;

public class StateEngine {

    private EnumSet states;

    private EnumSet events;

    private Enum currentState;

    private List<StateChangeObserver> observers = new ArrayList<>();

    private Map<Enum, List<StateChangeObserver>> stateObservers = new HashMap<>();

    private Map<Enum, Map<Enum, Enum>> transitions;

    public StateEngine (EnumSet states, EnumSet events, Enum startState) {
        if (!states.contains(startState)) {
            throw new IllegalArgumentException("Start State must be one of the defined states");
        }

        this.states = states;
        this.events = events;
        this.currentState = startState;
        transitions = new HashMap<>();
        Iterator<Enum> statesIterator = states.iterator();

        while(statesIterator.hasNext()) {
            transitions.put(statesIterator.next(), new HashMap<>());
        }
    }

    public void addTransition (Enum startState, Enum endState, Enum event) {
        transitions.get(startState).put(event, endState);
    }

    public Enum getCurrentState () {
        return currentState;
    }

    public void performAction (Enum action) {
        Enum nextState = transitions.get(currentState).get(action);

        if (nextState != null) {
            currentState = nextState;
            notifyAllObservers(currentState);
        }
    }

    private void notifyAllObservers (Enum state) {
        observers.stream().forEach(observer -> observer.onStateChange(state));

        if (stateObservers.get(state) != null) {
            stateObservers.get(state).stream().forEach(observer -> observer.onStateChange(state));
        }
    }

    public void addObserver (StateChangeObserver observer) {
        observers.add(observer);
    }

    public void addObserver (Enum state, StateChangeObserver observer) {
        if (stateObservers.get(state) == null) {
            stateObservers.put(state, new ArrayList());
        }
        stateObservers.get(state).add(observer);
    }

}
