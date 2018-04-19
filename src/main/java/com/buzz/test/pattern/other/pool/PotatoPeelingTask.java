package com.buzz.test.pattern.other.pool;

public class PotatoPeelingTask extends Task {

    private static final int TIME_PER_POTATO = 200;

    public PotatoPeelingTask(int numberOfPotatos) {
        super(numberOfPotatos * TIME_PER_POTATO);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
    }
}
