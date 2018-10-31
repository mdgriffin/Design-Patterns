package com.mgriffin.stateengine;

import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class StateEngineTest {

    private StateEngine stateEngine;

    @Before
    public void setup () {
        stateEngine =  new StateEngine.StateEngineBuilder()
            .setStates(EnumSet.allOf(States.class))
            .setEvents(EnumSet.allOf(Events.class))
            .setStartState(States.ALIVE)
            .addTransition(States.ALIVE, States.DEAD, Events.SHOOT)
            .build();
    }

    @Test
    public void stateEngineInstantiated () {
        assertNotNull(stateEngine);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddingInvalidStartState_exceptionThrown() {
        StateEngine se = new StateEngine.StateEngineBuilder()
                .setStates(EnumSet.allOf(States.class))
                .setEvents(EnumSet.allOf(Events.class))
                .setStartState(OtherStates.OPEN)
                .addTransition(States.ALIVE, States.DEAD, Events.SHOOT)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSameEnumUsedForBothStatesAndEvents_exceptionThrown() {
        StateEngine se = new StateEngine.StateEngineBuilder()
                .setStates(EnumSet.allOf(States.class))
                .setEvents(EnumSet.allOf(States.class))
                .setStartState(OtherStates.OPEN)
                .addTransition(States.ALIVE, States.DEAD, Events.SHOOT)
                .build();
    }

    @Test
    public void whenCreatingStateEngine_currentStateIsStartState () {
        assertEquals(States.ALIVE, stateEngine.getCurrentState());
    }


    @Test
    public void whenPerformingAction_currentStateChanged () {
        assertEquals(States.ALIVE, stateEngine.getCurrentState());

        stateEngine.performAction(Events.SHOOT);

        assertEquals(States.DEAD, stateEngine.getCurrentState());

        stateEngine.performAction(Events.SHOOT);
        assertEquals(States.DEAD, stateEngine.getCurrentState());
    }

    @Test
    public void canObserveChanges () {
        stateEngine.addObserver(state -> {
            assertEquals(States.DEAD ,state);
        });
        stateEngine.performAction(Events.SHOOT);
    }

    @Test
    public void canObserverSpecificStateChange () {
        stateEngine.addObserver(States.DEAD, state -> {
            assertEquals(States.DEAD ,state);
        });
        stateEngine.performAction(Events.SHOOT);
    }

    private enum States {
        ALIVE,
        DEAD
    }

    private enum OtherStates{
        OPEN,
        CLOSED
    }

    private enum Events {
        SHOOT
    }

}
