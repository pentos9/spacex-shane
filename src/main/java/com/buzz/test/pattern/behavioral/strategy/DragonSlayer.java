package com.buzz.test.pattern.behavioral.strategy;

public class DragonSlayer {

    private DragonSlayingStrategy slayingStrategy;

    public DragonSlayer(DragonSlayingStrategy slayingStrategy) {
        this.slayingStrategy = slayingStrategy;
    }

    public void goToBattle() {
        slayingStrategy.execute();
    }

    public void changeStrategy(DragonSlayingStrategy dragonSlayingStrategy) {
        this.slayingStrategy = dragonSlayingStrategy;
    }
}
