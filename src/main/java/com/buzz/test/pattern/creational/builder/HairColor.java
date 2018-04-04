package com.buzz.test.pattern.creational.builder;

public enum HairColor {
    WHITE, BLACK, RED, BLOND, BROWN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
