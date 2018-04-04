package com.buzz.test.pattern.prototype;

import java.lang.ref.PhantomReference;

public class Device implements Cloneable {
    protected String name;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Device clone() throws CloneNotSupportedException {
        return new Device(name);
    }
}
