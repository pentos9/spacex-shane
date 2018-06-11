package com.buzz.test.pattern.other.polymorphism.instance;

import com.buzz.test.pattern.other.polymorphism.Animal;

public class Dog implements Animal {
    @Override
    public int legs() {
        return 4;
    }
}
