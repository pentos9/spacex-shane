package com.buzz.common.switcher;

public class Switcher {
    private boolean on;
    private String name;

    public Switcher(String name, boolean on) {
        this.name = name;
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void onSwitcher() {
        this.on = true;
    }

    public void offSwitcher() {
        this.on = false;
    }
}
