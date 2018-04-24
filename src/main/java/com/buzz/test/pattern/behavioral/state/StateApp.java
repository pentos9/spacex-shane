package com.buzz.test.pattern.behavioral.state;

public class StateApp {
    public static void main(String[] args) {
        Mammoth mammoth = new Mammoth();
        mammoth.observe();
        mammoth.timePasses();
        mammoth.observe();
        mammoth.timePasses();
        mammoth.observe();
    }
}
