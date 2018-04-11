package com.buzz.test.pattern.behavioral.observer.generic;

import com.buzz.test.pattern.behavioral.observer.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GHobbits implements Race {

    private Logger logger = LoggerFactory.getLogger(GHobbits.class);

    @Override
    public void update(GWeather subject, WeatherType weatherType) {

        switch (weatherType) {
            case SUNNY:
                logger.info("The happy hobbits bade in the warm sun.");
                break;

            case WINDY:
                logger.info("The hobbits hold their hats tightly in the windy weather.");
                break;

            case COLD:
                logger.info("The hobbits are shivering in the cold weather.");
                break;

            case RAINY:
                logger.info("The hobbits look for cover from the rain.");
                break;

            default:
                break;
        }

    }
}
