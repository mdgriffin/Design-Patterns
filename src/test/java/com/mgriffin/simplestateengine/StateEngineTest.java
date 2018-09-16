package com.mgriffin.simplestateengine;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EnumSet;

import static junit.framework.TestCase.fail;

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

    private enum States {
        ALIVE,
        DEAD
    }

    private enum OtherStates {
        OPEN,
        CLOSED
    }

    private enum Events {
        SHOOT
    }

}
