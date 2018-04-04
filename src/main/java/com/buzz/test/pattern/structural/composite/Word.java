package com.buzz.test.pattern.structural.composite;


import java.util.List;

public class Word extends LetterComposite {

    public Word(List<Letter> letters) {
        for (Letter letter : letters) {
            this.add(letter);
        }
    }

    @Override
    public void printThisBefore() {
        System.out.print(" ");
    }
}
