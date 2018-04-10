package com.buzz.test.pattern.behavioral.strategy;


public class StrategyApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DragonSlayer slayer = new DragonSlayer(new MeleeStrategy());
        slayer.goToBattle();

        slayer.changeStrategy(new ProjectileStrategy());
        slayer.goToBattle();

        slayer.changeStrategy(new SpellStrategy());
        slayer.goToBattle();

        slayer.changeStrategy(() -> {
            System.out.println("You cut the dragon's head with sword and hammer");
        });

        slayer.goToBattle();
    }
}
