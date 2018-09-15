package com.mgriffin.simplestateengine;

import org.junit.Test;

import static junit.framework.TestCase.fail;

public class StateEngineTest {

    private States states;
    private Events events;

    @Test
    public void stateEngineShouldInstantiate () {
        StateEngine se = new StateEngine(states, events);
    }

    private enum States {
        alive,
        dead
    }

    private enum Events {
        shoot
    }

}
