package com.buzz.test.pattern.other.valueobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeroStatApp {

    private static Logger logger = LoggerFactory.getLogger(HeroStatApp.class);

    public static void main(String[] args) {
        HeroStat heroStatA = new HeroStat(10, 5, 0);
        HeroStat heroStatB = new HeroStat(10, 5, 0);
        HeroStat heroStatC = new HeroStat(5, 1, 8);

        logger.info("statA to string:{}", heroStatA.toString());
        logger.info("is A and B equals:{}", heroStatA.equals(heroStatB));
        logger.info("is B and C equals：{}", heroStatB.equals(heroStatC));

    }
}
