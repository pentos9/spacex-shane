package com.buzz.test.pattern.builder;

public enum HairColor {
    WHITE, BLACK, RED, BLOND, BROWN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
