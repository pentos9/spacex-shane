package com.buzz.test.pattern.structural.composite;

import java.util.List;

public class Sentence extends LetterComposite {
    public Sentence(List<Word> words) {
        for (Word word : words) {
            this.add(word);
        }
    }

    @Override
    public void printThisAfter() {
        System.out.println(".");
    }
}
