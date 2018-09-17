package com.mgriffin.simplestateengine;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;

public class StateEngineTest {


    @Test
    public void stateEngineShouldInstantiate () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), States.ALIVE);
    }

    @Test
    public void whenAddingATransition_noExceptionThrown () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), States.ALIVE);
        se.addTransition(States.ALIVE, States.DEAD, Events.SHOOT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddingInvalidStartState_exceptionThrown() {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), OtherStates.OPEN);
    }

    @Test
    public void whenCreatingStateEngine_currentStateIsStartState () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), States.ALIVE);
        assertEquals(States.ALIVE, se.getCurrentState());
    }

    @Test
    public void whenPerformingAction_currentStateChanged () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), States.ALIVE);

        assertEquals(States.ALIVE, se.getCurrentState());

        se.addTransition(States.ALIVE, States.DEAD, Events.SHOOT);
        se.performAction(Events.SHOOT);

        assertEquals(States.DEAD, se.getCurrentState());

        se.performAction(Events.SHOOT);
        assertEquals(States.DEAD, se.getCurrentState());
    }

    private enum States {
        ALIVE,
        DEAD
    }

    @Test
    public void canObserveChanges () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class), States.ALIVE);

        se.addTransition(States.ALIVE, States.DEAD, Events.SHOOT);
        se.addObserver(state -> {
            System.out.println("This is in the state change observer");
            assertEquals(States.DEAD ,state);
        });
        se.performAction(Events.SHOOT);
    }

    private enum OtherStates {
        OPEN,
        CLOSED
    }

    private enum Events {
        SHOOT
    }

}
