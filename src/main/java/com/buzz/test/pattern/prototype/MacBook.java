package com.buzz.test.pattern.prototype;

public class MacBook extends Device {

    private Color color;

    public MacBook(String name) {
        super(name);
    }

    public MacBook(String name, Color color) {
        super(name);
        this.color = color;
    }

    @Override
    protected MacBook clone() throws CloneNotSupportedException {
        return new MacBook(name, color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "MacBook{" +
                "color=" + color +
                '}';
    }
}
