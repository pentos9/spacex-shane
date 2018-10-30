package com.buzz.test.core.optional.car;

import java.util.Optional;

public class Person {
    Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
