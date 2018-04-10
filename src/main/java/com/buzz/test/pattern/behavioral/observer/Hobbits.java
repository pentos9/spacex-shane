package com.buzz.test.pattern.behavioral.observer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hobbits implements WeatherObserver {

    private Logger logger = LoggerFactory.getLogger(Hobbits.class);

    @Override
    public void update(WeatherType weatherType) {
        switch (weatherType) {
            case SUNNY:
                logger.info("the happy hobbits bade in the warm sun.");
                break;
            case RAINY:
                logger.info("the hobbits look for cover from the rain.");
                break;
            case COLD:
                logger.info("the hobbits are shivering in the cold water.");
                break;
            case WINDY:
                logger.info("the hobbits hold their hats tightly in the windy weather.");
                break;
            default:
                break;
        }

    }
}
