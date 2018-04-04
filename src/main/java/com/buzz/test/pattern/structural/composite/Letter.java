package com.buzz.test.pattern.structural.composite;

public class Letter extends LetterComposite {
    private char c;

    public Letter(char c) {
        this.c = c;
    }

    @Override
    public void printThisBefore() {
        System.out.print(c);
    }
}
