package com.buzz.test.pattern.prototype;

public class Pad extends Device {
    private Color color;

    public Pad(String name, Color color) {
        super(name);
        this.color = color;
    }

    @Override
    protected Pad clone() throws CloneNotSupportedException {
        return new Pad(this.name, this.color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Pad{" +
                "color=" + color +
                '}';
    }
}
