package com.buzz.test.pattern.other.polymorphism.instance;

import com.buzz.test.pattern.other.polymorphism.Animal;

public class Crab implements Animal {
    @Override
    public int legs() {
        return 8;
    }
}
