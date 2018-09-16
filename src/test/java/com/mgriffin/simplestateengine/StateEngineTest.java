package com.mgriffin.simplestateengine;

import org.junit.Test;

import java.util.EnumSet;

import static junit.framework.TestCase.fail;

public class StateEngineTest {

    private States states;
    private Events events;

    @Test
    public void stateEngineShouldInstantiate () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class));
    }

    @Test
    public void whenAddingATransition_noExceptionThrown () {
        StateEngine se = new StateEngine(EnumSet.allOf(States.class), EnumSet.allOf(Events.class));
        se.addTransition(States.ALIVE, States.DEAD, Events.SHOOT);
    }

    private enum States {
        ALIVE,
        DEAD
    }

    private enum Events {
        SHOOT
    }

}
