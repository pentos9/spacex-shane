package com.buzz.test.pattern.structural.composite;

public class CompositeApp {
    public static void main(String[] args) {
        LetterComposite orcMessage = new Message().messageFromOrcs();
        orcMessage.print();

        LetterComposite elfMessage = new Message().messageFromElf();
        elfMessage.print();

    }
}
