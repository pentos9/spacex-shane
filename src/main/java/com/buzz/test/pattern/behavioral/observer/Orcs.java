package com.buzz.test.pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.buzz.test.pattern.behavioral.observer.WeatherType.*;

public class Orcs implements WeatherObserver {

    private Logger logger = LoggerFactory.getLogger(Orcs.class);

    @Override
    public void update(WeatherType weatherType) {

        switch (weatherType) {
            case SUNNY:
                logger.info("the sun hurts the orcs's eyes.");
                break;
            case COLD:
                logger.info("the orcs is freezing cold");
                break;
            case WINDY:
                logger.info("the orcs smell almost vanishes in the wind.");
                break;
            case RAINY:
                logger.info("the orcs is dripping wet");
                break;
            default:
                break;
        }

    }
}
