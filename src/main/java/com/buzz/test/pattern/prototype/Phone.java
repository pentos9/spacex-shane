package com.buzz.test.pattern.prototype;

public class Phone extends Device {

    private Color color;

    public Phone(String name) {
        super(name);
    }

    public Phone(String name, Color color) {
        super(name);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "color=" + color +
                '}';
    }

    @Override
    protected Phone clone() throws CloneNotSupportedException {
        return new Phone(name, color);
    }
}
