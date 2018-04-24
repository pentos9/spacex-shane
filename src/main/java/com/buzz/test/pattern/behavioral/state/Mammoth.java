package com.buzz.test.pattern.behavioral.state;

public class Mammoth {
    private State state;

    public Mammoth() {
        state = new PeacefulState(this);
    }

    public void timePasses() {
        if (state.getClass().equals(State.class)) {
            changeStateTo(new AngryState(this));
        } else {
            changeStateTo(new PeacefulState(this));
        }
    }

    private void changeStateTo(State newState) {
        this.state = newState;
        this.state.onEnterState();
    }

    @Override
    public String toString() {
        return "Mammoth{" +
                "state=" + state +
                '}';
    }

    public void observe() {
        this.state.observe();
    }
}
