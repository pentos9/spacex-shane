package com.buzz.test.pattern.behavioral.command;

public class CommandApp {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        Goblin goblin = new Goblin();

        goblin.printStatus();

        wizard.castSpell(new ShrinkSpell(), goblin);
        goblin.printStatus();


        wizard.castSpell(new InvisibilitySpell(), goblin);
        wizard.undoStackSpell();
        goblin.printStatus();

        wizard.undoStackSpell();
        goblin.printStatus();

        wizard.redoStackSpell();
        goblin.printStatus();

        wizard.redoStackSpell();
        goblin.printStatus();
    }
}
