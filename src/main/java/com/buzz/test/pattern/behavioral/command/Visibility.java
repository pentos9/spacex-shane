package com.buzz.test.pattern.behavioral.command;

public enum Visibility {
    VISIBLE("visible"), INVISIBLE("invisible");

    private String title;

    Visibility(String title) {
        this.title = title;
    }
}
