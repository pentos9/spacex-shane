package com.buzz.test.pattern.structural.decorator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecoratorApp {

    private static Logger logger = LoggerFactory.getLogger(DecoratorApp.class);

    public static void main(String[] args) {
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();

        Troll clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();

        Troll swordTroll = new SwordTroll(clubbedTroll);
        swordTroll.attack();
        swordTroll.fleeBattle();
        logger.info("attack value:" + swordTroll.getAttack());
    }
}
