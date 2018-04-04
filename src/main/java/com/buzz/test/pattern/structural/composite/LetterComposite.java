package com.buzz.test.pattern.structural.composite;


import java.util.ArrayList;
import java.util.List;

public abstract class LetterComposite {

    private List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letterComposite) {
        children.add(letterComposite);
    }

    public int count() {
        return children.size();
    }

    public void printThisBefore() {
    }

    public void printThisAfter() {
    }

    public void print() {
        printThisBefore();

        for (LetterComposite letterComposite : children) {
            letterComposite.print();
        }

        printThisAfter();
    }
}
