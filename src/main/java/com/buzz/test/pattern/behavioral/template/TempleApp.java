package com.buzz.test.pattern.behavioral.template;

public class TempleApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        HalflingThief thief = new HalflingThief(new HitAndReturnMethod());
        thief.steal();

        thief.changeMethod(new SubtleMethod());
        thief.steal();
    }
}
