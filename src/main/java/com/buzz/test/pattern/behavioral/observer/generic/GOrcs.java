package com.buzz.test.pattern.behavioral.observer.generic;


import com.buzz.test.pattern.behavioral.observer.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GOrcs implements Race {

    private Logger logger = LoggerFactory.getLogger(GOrcs.class);

    @Override
    public void update(GWeather subject, WeatherType weatherType) {

        switch (weatherType) {
            case SUNNY:
                logger.info("The sun hurts the orcs' eyes.");
                break;

            case COLD:
                logger.info("The orcs are freezing cold.");
                break;

            case WINDY:
                logger.info("The orc smell almost vanishes in the wind.");
                break;

            case RAINY:
                logger.info("The orcs are dripping wet.");
                break;

            default:
                break;
        }

    }
}
