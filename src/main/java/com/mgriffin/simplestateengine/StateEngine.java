package com.mgriffin.simplestateengine;

import java.util.*;

public class StateEngine implements StateChangeObservable {

    private final EnumSet states;

    private final EnumSet events;

    private Enum currentState;

    private List<StateChangeObserver> observers = new ArrayList<>();

    private Map<Enum, List<StateChangeObserver>> stateObservers = new HashMap<>();

    private Map<Enum, Map<Enum, Enum>> transitions;

    private StateEngine (EnumSet states, EnumSet events, Enum startState, Map<Enum, Map<Enum, Enum>> transitions) {
        this.states = states;
        this.events = events;
        this.currentState = startState;
        this.transitions = transitions;
    }

    public Enum getCurrentState () {
        return currentState;
    }

    public void performAction (Enum action) {
        if (transitions.get(currentState) != null && transitions.get(currentState).get(action) != null) {
            Enum nextState = transitions.get(currentState).get(action);

            if (nextState != null) {
                currentState = nextState;
                notifyAllObservers(currentState);
            }
        }
    }

    private void notifyAllObservers (Enum state) {
        observers.stream().forEach(observer -> observer.onStateChange(state));

        if (stateObservers.get(state) != null) {
            stateObservers.get(state).stream().forEach(observer -> observer.onStateChange(state));
        }
    }

    @Override
    public void addObserver (StateChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void addObserver (Enum state, StateChangeObserver observer) {
        if (stateObservers.get(state) == null) {
            stateObservers.put(state, new ArrayList());
        }
        stateObservers.get(state).add(observer);
    }

    @Override
    public void removeObserver (StateChangeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void removeObserver (Enum state, StateChangeObserver observer) {
        stateObservers.get(state).remove(observer);
    }

    public static class StateEngineBuilder {

        private EnumSet states;

        private EnumSet events;

        private Enum startState;

        private Map<Enum, Map<Enum, Enum>> transitions = new HashMap<>();

        public StateEngineBuilder () {}

        public StateEngineBuilder setStates (EnumSet states) {
            this.states = states;
            return this;
        }

        public StateEngineBuilder setEvents (EnumSet events) {
            this.events = events;
            return this;
        }

        public StateEngineBuilder setStartState (Enum startState) {
            this.startState = startState;
            return this;
        }

        public StateEngineBuilder addTransition (Enum startState, Enum endState, Enum event) {
            if (transitions.get(startState) == null) {
                transitions.put(startState, new HashMap<>());
            }
            transitions.get(startState).put(event, endState);

            return this;
        }

        public StateEngine build () {
            if (states == null || events == null || startState == null || transitions == null || !states.contains(startState)) {
                throw new IllegalArgumentException();
            }
            return new StateEngine(states, events, startState, transitions);
        }
    }

}
